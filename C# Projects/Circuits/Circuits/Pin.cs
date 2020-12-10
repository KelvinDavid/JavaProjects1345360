using System;
using System.Collections.Generic;
using System.Text;
using System.Drawing;

namespace Circuits
{
    /// <summary>
    /// Each Pin represents an input or an output of a gate.
    /// Every Pin knows which gate it belongs to
    /// (and the Gate property returns this).
    /// 
    /// Input pins can be connected to at most one wire
    /// (see the InputWire property).
    /// 
    /// Output pins may have lots of wires pointing to them,
    /// but they don't know anything about this.
    /// </summary>
    public class Pin
    {
        protected int x,y;
        protected bool input;
        protected int length;
        protected Gate owner;
        protected Wire connection;

        public Pin(Gate gate, bool input, int length)
        {
            this.owner = gate;
            this.input = input;
            this.length = length;
        }
        /// <summary>
        /// Gets and sets the Length value
        /// </summary>
        public int Length
        {
            get { return length; }
        }

        /// <summary>
        /// A read-only property that returns true for input pins
        /// and false for output pins.
        /// </summary>
        public bool IsInput
        {
            get { return input; }
        }

        /// <summary>
        /// Returns true for output pins, false for input pins.
        /// </summary>
        public bool IsOutput
        {
            get { return !input; }
        }

        /// <summary>
        /// This read-only property returns the gate that this pin
        /// belongs to.
        /// </summary>
        public Gate Owner
        {
            get { return owner; }
        }

        /// <summary>
        /// For input pins, this gets or sets the wire that is coming
        /// into the pin.  (Input pins can only be connected to one wire)
        /// For output pins, sets are ignored and get always returns null.
        /// </summary>
        public Wire InputWire
        {
            get
            {
                return connection;
            }
            set
            {
                if (input)
                {
                    connection = value;
                }
            }
        }

        /// <summary>
        /// Get or set the X position of this pin.
        /// For input pins, this is at the left hand side of the pin.
        /// For output pins, this is at the right hand side.
        /// </summary>
        public int X
        {
            get { return x; }
            set { x = value; }
        }

        /// <summary>
        /// Get or set the Y position of this pin.
        /// </summary>
        public int Y
        {
            get { return y; }
            set { y = value; }
        }

        /// <summary>
        /// True if (mouseX, mouseY) is within 3 pixels of the business
        /// end of the pin.
        /// </summary>
        /// <param name="mouseX">The X position of the mouse</param>
        /// <param name="mouseY">The Y position of the mouse</param>
        /// <returns>true if mouse is close to the main end of the pin</returns>
        public bool isMouseOn(int mouseX, int mouseY)
        {
            int diffX = mouseX - x;
            int diffY = mouseY - y;
            return diffX * diffX + diffY * diffY <= 5*5;
        }

        public void Draw(Graphics paper)
        {
            Brush brush = Brushes.DarkGray;
            if (input)
            {
                paper.FillRectangle(brush, x - 1, y - 1, length, 3);
            }
            else
            {
                paper.FillRectangle(brush, x - length + 1, y - 1, length, 3);
            }
        }

        public override string ToString()
        {
            if (input)
                return "InPin(" + x + "," + y + ")";
            else
                return "OutPin(" + x + "," + y + ")";
        }
    }
}
