using System;
using System.Collections.Generic;
using System.Text;
using System.Drawing;
using System.Diagnostics;

namespace Circuits
{
    /// <summary>
    /// This is a subclass of GATES and holds the variables and methods
    /// of the InputSource (aka. Swtich)
    /// </summary>
    class InputSource : Gate
    {
        private bool voltzStatus = false; //This holds the on/off status
        Brush onBrush = Brushes.LightGreen; //This is the color that indicates on
        Brush offBrush = Brushes.Green; //This is the color that indicates off

        public InputSource(int x, int y) : base(x, y)
        {
            pins.Add(new Pin(this, false, 20));
        }
        /// <summary>
        /// This property returns the on/off status of the switch
        /// </summary>
        public bool status { get { return voltzStatus; } }

        public override void Draw(Graphics paper)
        {
            Brush brush = offBrush;
            if (selected) //Checks if selected and determines if it's going to be on/off
            {
                if (voltzStatus == false) //Turns on if off
                {
                    voltzStatus = true;
                }
                else
                {
                    voltzStatus = false; //Turns off if on
                }
                Selected = false;
            }
            if (voltzStatus == true) //indicate user its on
            {
                brush = onBrush;
            }
            else if (voltzStatus == false) //indicate user its off
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
            pins[0].X = x + WIDTH + GAP;
            pins[0].Y = y + HEIGHT / 2;
        }

        public override bool Evaluate()
        {
            return voltzStatus;
        }

        public override Gate Clone()
        {
            if (selected)
            {
                return new InputSource(Left, Top);
            }
            else
            {
                return null;
            }
        }

    }
}
