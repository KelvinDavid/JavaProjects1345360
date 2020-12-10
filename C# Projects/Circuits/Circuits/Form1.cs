using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Diagnostics;

namespace Circuits
{
    //Name: Kelvin David
    //ID: 1345360

    /// <summary>
    /// The main GUI for the COMP104 digital circuits editor.
    /// This has a toolbar, containing buttons called buttonAnd, buttonOr, etc.
    /// The contents of the circuit are drawn directly onto the form.
    /// 
    /// ========================================================================
    /// Questions & Answers
    /// ========================================================================
    /// 1.Is it a better idea to fully document the Gate class or the AndGate
    /// subclass? Can you inherit comments?
    /// 
    /// No subclasses cannot inherrit comments from their parent classes. Though it
    /// is still better to fully document the parent class as because some of the
    /// methods are used by the subclasses and can use the UML comments.
    /// 
    /// 2.What is the advantage of making a method abstract in the superclass
    /// rather than just writing a virtual method with no code in the body of
    /// the method? Is there any disadvantage to an abstract method?
    /// 
    /// Having and method abstract in the superclass forces the other subclasses
    /// to create that method within their own classes. A empty virutal method 
    /// would not force subclasses to have the method but the abstract will. This
    /// gives the abstract method the advantage when it comes to making sure that
    /// all classes have the needed abstract method (as it will error otherwise).
    /// Though the disadvantage to abstract methods is that if some classes use 
    /// the same method with no change you would have to copy&paste that method in
    /// each of the given classes. In that case virtual would be at an advantage as
    /// it allows a method to be used by all subclasses.
    /// 
    /// 3.If a class has an abstract method in it, does the class have to be
    /// abstract?
    /// 
    /// Yes. If the class is not abstract then the program will return an error
    /// if you create a abstract method in it.
    /// 
    /// 4.What would happen in your program if one of the gates added to your
    /// Compound Gate is another Compound Gate? Is your design robust
    /// enough to cope with this situation?
    /// 
    /// No, my given design would not be robust enough to cope with this situation.
    /// If a gate is added to another compound (whilist it's already assigned to one)
    /// it would end up combining both compund gates and instead create a large
    /// compound gate. So the compound A that is being added to compound B it will be
    /// stored into compound B's gate list and would be identified as a singular gate.
    /// 
    /// =======================================================================
    /// </summary>
    public partial class Form1 : Form
    {
        /// <summary>
        /// The (x,y) mouse position of the last MouseDown event.
        /// </summary>
        protected int startX, startY;

        /// <summary>
        /// If this is non-null, we are inserting a wire by
        /// dragging the mouse from startPin to some output Pin.
        /// </summary>
        protected Pin startPin = null;

        /// <summary>
        /// The (x,y) position of the current gate, just before we started dragging it.
        /// </summary>
        protected int currentX, currentY;

        /// <summary>
        /// The set of gates in the circuit
        /// </summary>
        protected List<Gate> gates = new List<Gate>();

        /// <summary>
        /// The set of connector wires in the circuit
        /// </summary>
        protected List<Wire> wires = new List<Wire>();

        /// <summary>
        /// The currently selected gate, or null if no gate is selected.
        /// </summary>
        protected Gate current = null;

        /// <summary>
        /// The new gate that is about to be inserted into the circuit
        /// </summary>
        protected Gate newGate = null;

        /// <summary>
        /// The new compound gate that the gates are inserted into 
        /// </summary>
        private Compound newCompound = null;

        public Form1()
        {
            InitializeComponent();
            DoubleBuffered = true;
        }

        /// <summary>
        /// Finds the pin that is close to (x,y), or returns
        /// null if there are no pins close to the position.
        /// </summary>
        /// <param name="x"></param>
        /// <param name="y"></param>
        /// <returns></returns>
        public Pin findPin(int x, int y)
        {
            foreach (Gate g in gates)
            {
                foreach (Pin p in g.Pins)
                {
                    if (p.isMouseOn(x, y))
                        return p;
                }
            }
            return null;
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            foreach (Gate g in gates)
            {
                g.Draw(e.Graphics); //Redraws all gates into the circuit
            } 
            foreach (Wire w in wires)
            {
                w.Draw(e.Graphics); //Redraws all the wires into the circuit
            }
            if (startPin != null)
            {
                e.Graphics.DrawLine(Pens.White, //Draws wire from start to end pin
                    startPin.X, startPin.Y, 
                    currentX, currentY);
            }
            if (newGate != null) //If a new gate is being created
            {
                // show the gate that we are dragging into the circuit
                newGate.MoveTo(currentX, currentY);
                newGate.Draw(e.Graphics);
            }
        }
        /// <summary>
        /// This method runs when mouse is clicks onto
        /// the form
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Form1_MouseClick(object sender, MouseEventArgs e)
        {
            if (current != null) //If a gate is selected
            {
                current.Selected = false;
                current = null;
                this.Invalidate();
            }
            // See if we are inserting a new gate
            if (newGate != null)
            {
                newGate.MoveTo(e.X, e.Y);
                gates.Add(newGate);
                newGate = null;
                this.Invalidate();
            }
            else
            {
                // search for the first gate under the mouse position
                foreach (Gate g in gates)
                {
                    if (g.IsMouseOn(e.X, e.Y))
                    {
                        //////////////////////////////////////////////// Modifed code to add and error check gates
                        if (newCompound != null) //This checks if the user is creating a new compound
                        {
                            if (newCompound.compGates.Contains(g)) //Checks if the gate has already been added
                            {
                                MessageBox.Show("This gate has already been added");
                            }
                            else
                            {
                                MessageBox.Show("Gate added to compound");
                                newCompound.AddGate(g); //Adds gate to compound list
                            }
                        }
                        ///////////////////////////////////////////////// that are being added to the compound
                        g.Selected = true;
                        current = g;
                        this.Invalidate();
                        break;
                    }
                }
            }
        }
        /// <summary>
        /// This event method occurs when the mouse is held down
        /// onto the form
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Form1_MouseDown(object sender, MouseEventArgs e)
        {
            if (current == null)
            {
                // try to start adding a wire
                startPin = findPin(e.X, e.Y);
            }
            else if (current.IsMouseOn(e.X, e.Y))
            {
                // start dragging the current object around
                startX = e.X;
                startY = e.Y;
                currentX = current.Left;
                currentY = current.Top;
            }
        }
        /// <summary>
        /// This event method occurs when the mouse is
        /// moved (mostly when also being held down)
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Form1_MouseMove(object sender, MouseEventArgs e)
        {
            if (startPin != null)
            {
                Debug.WriteLine("wire from "+startPin+" to " + e.X + "," + e.Y);
                currentX = e.X;
                currentY = e.Y;
                this.Invalidate();  // this will draw the line
            }
            else if (startX >= 0 && startY >= 0 && current != null)
            {
                Debug.WriteLine("mouse move to " + e.X + "," + e.Y);
                current.MoveTo(currentX + (e.X - startX), currentY + (e.Y - startY));
                this.Invalidate();
            }
            else if (newGate != null)
            {
                currentX = e.X;
                currentY = e.Y;
                this.Invalidate();
            }
        }
        /// <summary>
        /// Creates new OR gate
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonOr_Click(object sender, EventArgs e)
        {
            newGate = new OrGate(0, 0, imageListGates.Images[0], imageListGates.Images[1]);
        }
        /// <summary>
        /// Creates new NOT gate
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonNot_Click(object sender, EventArgs e)
        {
            newGate = new NotGate(0, 0, imageListGates.Images[2], imageListGates.Images[3]);
        }
        /// <summary>
        /// Creates new PAD gate
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonPAD_Click(object sender, EventArgs e)
        {
            newGate = new PADGate(0, 0);
        }
        /// <summary>
        /// Creates new Switch
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void switchToolStripMenuItem_Click(object sender, EventArgs e)
        {
            newGate = new InputSource(0, 0);
        }
        /// <summary>
        /// Creates new lamp
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void lampToolStripMenuItem_Click(object sender, EventArgs e)
        {
            newGate = new OutputSource(0, 0);
        }
        /// <summary>
        /// This method evaluates all the output sources in the form
        /// and return whether or not they are on or off dependant on the
        /// given gates and input sources
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void evaluateToolStripMenuItem_Click(object sender, EventArgs e)
        {
            foreach (Gate g in gates)
            {
                if (g is OutputSource)
                {
                    g.Evaluate();
                }
            }
            this.Invalidate();
        }
        /// <summary>
        /// This method is responsible for the Copy&Paste function that allows 
        /// the user copy both individual gates and compound gates
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void copyToolStripMenuItem_Click(object sender, EventArgs e)
        {
            foreach (Gate g in gates)
            {
                if (g.Selected == true) //This clones the selected gate
                {
                    newGate = g.Clone();
                    if (g is Compound) //This checks if the selected gate is compound
                    {
                        foreach (Pin p in newGate.Pins) //If so it will also copy/create and store new wires if they exist
                        {
                            if (p.InputWire != null) //If current pin has a Inputwire
                            {
                                wires.Add(p.InputWire);
                            }
                        }
                    }
                }
            }
            if (current == null) //This check if no gate has beens selected
            {
                MessageBox.Show("Please select a gate to copy");
            }
        }
        /// <summary>
        /// This method is responsible for creating a new compound gate and 
        /// allow the user to start selecting the given gates
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void startGroupToolStripMenuItem_Click(object sender, EventArgs e)
        {
            newCompound = new Compound(0, 0);
        }
        /// <summary>
        /// This method is reponsible for grouping the gates together and removing the
        /// individual instances of the given gates
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void endGroupToolStripMenuItem_Click(object sender, EventArgs e)
        {
            foreach (Gate g in newCompound.compGates) //Removes the compound gates from the list
            {
                gates.Remove(g);
            }
            newGate = newCompound; //Stores new compound into the gates variable
            newCompound = null;

        }
        /// <summary>
        /// This method is responsible for the event the user releases
        /// the mouse click in the form, it handles the whether or not 
        /// the user is trying to place a wire or not
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Form1_MouseUp(object sender, MouseEventArgs e)
        {
            if (startPin != null)
            {
                // see if we can insert a wire
                Pin endPin = findPin(e.X, e.Y);
                if (endPin != null)
                {
                    Debug.WriteLine("Trying to connect " + startPin + " to " + endPin);
                    Pin input, output;
                    if (startPin.IsOutput)
                    {
                        input = endPin;
                        output = startPin;
                    }
                    else
                    {
                        input = startPin;
                        output = endPin;
                    }
                    if (input.IsInput && output.IsOutput) //Checkin if pins need wire
                    {
                        if (input.InputWire == null) //If no wire is found
                        {
                            Wire newWire = new Wire(output, input);
                            input.InputWire = newWire;
                            wires.Add(newWire);
                        }
                        else
                        {
                            MessageBox.Show("That input is already used.");
                        }
                    }
                    else
                    {
                        MessageBox.Show("Error: you must connect an output pin to an input pin.");
                    }
                }
                startPin = null;
                this.Invalidate();
            }
            // We have finished moving/dragging
            startX = -1;
            startY = -1;
            currentX = 0;
            currentY = 0;
        }
        /// <summary>
        /// This method creates a AND gate
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonAnd_Click(object sender, EventArgs e)
        {
            newGate = new AndGate(0, 0);
        }
    }
}