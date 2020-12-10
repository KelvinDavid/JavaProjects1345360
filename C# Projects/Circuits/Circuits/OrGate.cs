using System;
using System.Collections.Generic;
using System.Text;
using System.Drawing;
using System.Diagnostics;

namespace Circuits
{
    /// <summary>
    /// This is a subclass of GATE that holds all the variables
    /// and methods for a OR gate
    /// </summary>
    class OrGate : Gate
    {
        private Image _orGatePlain; //The bitmap image of a non-highlighted OR gate
        private Image _orGateSelect; //The bitmap image of a highlighted OR gate
        public OrGate(int x, int y, Image imagePlain,
            Image imageSelect) : base(x, y)
        {
            _orGatePlain = imagePlain;
            _orGateSelect = imageSelect;
            pins.Add(new Pin(this, true, 20));
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
                paper.DrawImage(_orGateSelect, left, top);
            }
            else
            {
                paper.DrawImage(_orGatePlain, left, top);
            }
        }

        public override bool Evaluate()
        {
            Gate inputA = pins[0].InputWire.FromPin.Owner;
            Gate inputB = pins[1].InputWire.FromPin.Owner;
            return inputA.Evaluate() || inputB.Evaluate();
        }

        public override Gate Clone()
        {
            if (selected)
            {
                OrGate newOr = new OrGate(Left, Top, _orGatePlain, _orGateSelect);
                return newOr;
            }
            else
            {
                return null;
            }
        }
    }
}
