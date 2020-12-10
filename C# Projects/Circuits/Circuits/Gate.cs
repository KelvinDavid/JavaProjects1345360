using System;
using System.Collections.Generic;
using System.Text;
using System.Drawing;
using System.Diagnostics;

namespace Circuits
{
    /// <summary>
    /// This is the super class that holds all the base
    /// information, variables and methods that are shared amoung all the
    /// gate objects
    /// </summary>
    public abstract class Gate
    {
        // left is the left-hand edge of the main part of the gate.
        // So the input pins are further left than left.
        protected int left;

        // top is the top of the whole gate
        protected int top;

        // width and height of the main part of the gate
        protected const int WIDTH = 40;
        protected const int HEIGHT = 40;
        // length of the connector legs sticking out left and right
        protected const int GAP = 10;

        protected Brush selectedBrush = Brushes.Red;
        protected Brush normalBrush = Brushes.LightGray;

        /// <summary>
        /// This is the list of all the pins of this gate.
        /// An AND gate always has two input pins (0 and 1)
        /// and one output pin (number 2).
        /// </summary>
        protected List<Pin> pins = new List<Pin>();

        protected bool selected = false; //Is the gate selected

        public Gate(int x, int y) { }


        /// <summary>
        /// Indicates whether this gate is the current one selected.
        /// </summary>
        public virtual bool Selected
        {
            get { return selected; }
            set { selected = value; }
        }
        // left is the left-hand edge of the main part of the gate.
        // So the input pins are further left than left.
        public int Left
        {
            get { return left; }
        }
        // top is the top of the whole gate
        public int Top
        {
            get { return top; }
        }
        //The pins of the given gate
        public List<Pin> Pins
        {
            get { return pins; }
            set { pins = value; }
        }

        /// <summary>
        /// True if the given (x,y) position is roughly
        /// on top of this gate.
        /// </summary>
        /// <param name="x">Mouse's current x position</param>
        /// <param name="y">Mouse's current y position</param>
        /// <returns></returns>
        public virtual bool IsMouseOn(int x, int y)
        {
            if (left <= x && x < left + WIDTH
                && top <= y && y < top + HEIGHT)
                return true;
            else
                return false;
        }
        /// <summary>
        /// This method draws the gate into the form
        /// </summary>
        /// <param name="paper">picturebox to draw into</param>
        public abstract void Draw(Graphics paper);
        /// <summary>
        /// This determines how the pins and the gate is moving
        /// around the form
        /// </summary>
        /// <param name="x">Mouse X pos</param>
        /// <param name="y">Mouse y pos</param>
        public virtual void MoveTo(int x, int y)
        {
            Debug.WriteLine("pins = " + pins.Count);
            left = x;
            top = y;
            // must move the pins too
            pins[0].X = x - GAP;
            pins[0].Y = y + GAP;
            pins[1].X = x - GAP;
            pins[1].Y = y + HEIGHT - GAP;
            pins[2].X = x + WIDTH + GAP;
            pins[2].Y = y + HEIGHT / 2;
        }

        /// <summary>
        /// This method evaluates whether or not a gate is outputing
        /// true or false
        /// </summary>
        /// <returns></returns>
        public abstract bool Evaluate();

        /// <summary>
        /// This method is a copy&paste method that allows
        /// gates to be copied and pasted
        /// </summary>
        /// <returns></returns>
        public abstract Gate Clone();
    }
}
