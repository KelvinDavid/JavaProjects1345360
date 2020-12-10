using System;
using System.Collections.Generic;
using System.Text;
using System.Drawing;
using System.Diagnostics;
using System.Windows.Forms;

namespace Circuits
{
    /// <summary>
    /// This is a subclass of GATE that holds the variables and methods
    /// of the Output source (aka. Lamp)
    /// </summary>
    class OutputSource : Gate
    {
        private bool voltzStatus = false; //This holds the on/off status of the lamp
        Brush onBrush = Brushes.Yellow; //This is the color of a ON lamp
        Brush offBrush = Brushes.DarkGoldenrod; //This is the color of a OFF lamp

        public OutputSource(int x, int y) : base(x, y)
        {
            pins.Add(new Pin(this, true, 20));
        }

        public bool status { get { return voltzStatus; } } //This returns the on/off status of the lamp

        public override void Draw(Graphics paper)
        {
            Brush brush = offBrush;
            if (voltzStatus == true)
            {
                brush = onBrush;
            }
            else if (voltzStatus == false)
            {
                brush = offBrush;
            }
            foreach (Pin p in pins)
                p.Draw(paper);

            paper.FillRectangle(brush, left + 20, top + 10, 20, 20);
        }

        public override void MoveTo(int x, int y)
        {
            Debug.WriteLine("pins = " + pins.Count);
            left = x;
            top = y;
            // must move the pins too
            pins[0].X = x + WIDTH - 30;
            pins[0].Y = y + HEIGHT / 2;
        }

        public override bool Evaluate()
        {
            if (pins[0].InputWire != null) //Checks if there is a wire attached to the pin
            {
                Gate input = pins[0].InputWire.FromPin.Owner;
                voltzStatus = input.Evaluate(); //Sets voltz status to evaluation
                return input.Evaluate();
            }
            else
            {
                MessageBox.Show("ERROR || An output exists with no input ||");
                return false;
            }
        }

        public override Gate Clone()
        {
            if (selected)
            {
                return new OutputSource(Left, Top);
            }
            else
            {
                return null;
            }
        }
    }
}
