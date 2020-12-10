using System;
using System.Collections.Generic;
using System.Text;
using System.Diagnostics;
using System.Drawing;

namespace Circuits
{
    /// <summary>
    /// This is a subclass of GATE that holds all the variables
    /// and methods for a PAD gate
    /// </summary>
    class PADGate : Gate
    {
        public PADGate(int x, int y) : base(x, y)
        {
            pins.Add(new Pin(this, true, 20));
            pins.Add(new Pin(this, false, 20));
            MoveTo(x, y); // move the gate and position the pins
        }
        public override void Draw(Graphics paper)
        {
            Brush brush;
            if (selected)
            {
                brush = selectedBrush;
            }
            else
            {
                brush = normalBrush;
            }
            foreach (Pin p in pins)
                p.Draw(paper);

            // AND is simple, so we can use a circle plus a rectange.
            // An alternative would be to use a bitmap.
            paper.FillRectangle(brush, left, top + 10, WIDTH, HEIGHT / 2);

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
            //PAD gate is coded like a wire as long as there is voltage running through
            Gate input = pins[0].InputWire.FromPin.Owner;
            return input.Evaluate();
        }

        public override Gate Clone()
        {
            if (selected)
            {
                return new PADGate(Left, Top);
            }
            else
            {
                return null;
            }
        }

    }
}
