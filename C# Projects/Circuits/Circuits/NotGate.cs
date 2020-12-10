using System;
using System.Collections.Generic;
using System.Text;
using System.Drawing;
using System.Diagnostics;

namespace Circuits
{
    /// <summary>
    /// This is a subclass of GATE and holds the variables and
    /// methods for the NOT gate
    /// </summary>
    class NotGate : Gate
    {
        private Image _NotGatePlain; //The bitmap image of a non-highlighed NOT gate
        private Image _NotGateSelected; //The bitmap image of a highlighted NOT gate
        public NotGate(int x, int y, Image image, Image imageSelected) : base(x, y)
        {
            _NotGatePlain = image; //Assigns the given NOT gate images
            _NotGateSelected = imageSelected;
            pins.Add(new Pin(this, true, 20));
            pins.Add(new Pin(this, false, 20));
            MoveTo(x, y); // move the gate and position the pins
        }
        public override void Draw(Graphics paper)
        {
            foreach (Pin p in pins)
                p.Draw(paper);
            if (selected)
            {
                paper.DrawImage(_NotGateSelected, left, top);
            }
            else
            {
                paper.DrawImage(_NotGatePlain, left, top);
            }

        }
        public override void MoveTo(int x, int y)
        {
            Debug.WriteLine("pins = " + pins.Count);
            left = x;
            top = y;
            // must move the pins too
            pins[0].X = x - GAP;
            pins[0].Y = y + HEIGHT - 20;
            pins[1].X = x + WIDTH + GAP;
            pins[1].Y = y + HEIGHT / 2;
        }

        public override bool Evaluate()
        {
            Gate input = pins[0].InputWire.FromPin.Owner;
            return input.Evaluate() == false;
        }

        public override Gate Clone()
        {
            if (selected)
            {
                NotGate newNot = new NotGate(Left, Top, _NotGatePlain, _NotGateSelected);
                return newNot;
            }
            else
            {
                return null;
            }
        }
    }
}
