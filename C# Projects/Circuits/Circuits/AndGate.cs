using System;
using System.Collections.Generic;
using System.Text;
using System.Drawing;
using System.Diagnostics;

namespace Circuits
{
    /// <summary>
    /// This class implements an AND gate with two inputs
    /// and one output.
    /// </summary>
    public class AndGate : Gate
    {

        public AndGate(int x, int y) : base(x,y)
        {
            pins.Add(new Pin(this, true, 20));
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
            paper.FillEllipse(brush, left, top, WIDTH, HEIGHT);
            paper.FillRectangle(brush, left, top, WIDTH / 2, HEIGHT);
        }

        public override bool Evaluate()
        {
            Gate inputA = pins[0].InputWire.FromPin.Owner;
            Gate inputB = pins[1].InputWire.FromPin.Owner;
            return inputA.Evaluate() && inputB.Evaluate();
        }

        public override Gate Clone()
        {
            if (selected)
            {
                AndGate newAnd = new AndGate(Left, Top);
                return newAnd;
            }
            else
            {
                return null;
            }
        }
    }
}
