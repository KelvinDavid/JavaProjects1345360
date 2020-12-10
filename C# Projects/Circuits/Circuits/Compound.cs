using System;
using System.Collections.Generic;
using System.Text;
using System.Diagnostics;
using System.Drawing;
using System.Windows.Forms;

namespace Circuits
{
    /// <summary>
    /// This class implements the COMPOUND gate,
    /// it groups all the selected gates into one gate.
    /// </summary>
   class Compound : Gate
    {
        public List<Gate> compGates = new List<Gate>(); //This holds all the gates in the compound
        private int xReal; //The X coords of the connected gates to the base gate
        private int yReal;//The Y coords of the connected gates to the base gate

        public Compound(int x, int y) : base(x, y) { }

        public override void MoveTo(int x, int y)
        {
            left = compGates[0].Left; //Find the base gate
            top = compGates[0].Top;
            foreach (Gate g in compGates)
            {
                if (g.Left == left && g.Top == top) //Check if base gate
                {
                    g.MoveTo(x, y);
                }
                else
                {
                    xReal = x + (g.Left - left); //Movement for sub gates
                    yReal = y + (g.Top - top);
                    g.MoveTo(xReal, yReal);
                }
            }
        }

        public override bool IsMouseOn(int x, int y)
        {
            foreach (Gate g in compGates) //Checks if any of the gates a part of the compound
            {
                if (g.IsMouseOn(x, y)) 
                {
                    left = g.Left;
                    top = g.Top;
                    return true;
                }
            }
            return false;
        }
        /// <summary>
        /// This method adds the given gate
        /// to the compound list and sorts
        /// the compound list from left ----> right
        /// it also checks if the given gate already exists
        /// </summary>
        /// <param name="gate">The gate that is being added</param>
        public void AddGate(Gate gate)
        {
            compGates.Add(gate);
            compGates.Sort((x, y) => x.Left - y.Left); //Sorts the gates from Left ---> Right
            foreach (Pin p in gate.Pins) //Copies the pins into the compound list
            {
                pins.Add(p);
            }
        }

        public override bool Selected
        {
            get { return selected; }
            set
            {
                foreach (Gate g in compGates) //Selects all the gates in the compound gate
                {
                    g.Selected = value;
                }
                selected = value;
            }
        }

        public override bool Evaluate()
        {
            throw new NotImplementedException(); //Doesn't have a evaluation method
        }

        public override Gate Clone()
        {
            List<Pin> Output = new List<Pin>(); //holds all the Output pins
            List<Pin> Input = new List<Pin>(); //holds all the inputs pins
            Compound newComp = new Compound(0,0);//Creates the new compound
            for(int i = compGates.Count - 1; i >= 0; i--) //Goes through the compound gate list and copies each gate
            {
                Gate newGate = compGates[i].Clone(); //Clones all gates in this current compound
                newGate.Pins.Clear(); //Clears the pins from the new compound gate
                foreach (Pin p in compGates[i].Pins) //Goes through the pins of the given gate and copies them
                {
                    Pin copyPin = new Pin(newGate, p.IsInput, p.Length);
                    if (p.InputWire != null) //Checks if pin has a wire connected to it
                    {
                        Input.Add(copyPin); //Records current input of wire
                        Output.Add(p.InputWire.FromPin); //Records current output of wire
                    }
                    else
                    {
                        for(int j = 0; j < Output.Count;j++) //Goes through the known outputs and inputs and creates wires for each pair
                        {
                            if (Output[j] == p) //If current output matches with current input
                            {
                                Wire wNew = new Wire(copyPin, Input[j]); //Creates new wire
                                Input[j].InputWire = wNew; //Assigns the wire to the input
                            }
                        }
                    }
                    newGate.Pins.Add(copyPin); //Adds the copied pins to the new compound                    
                }
                newComp.AddGate(newGate); //Adds the copied gates to the new compound
            }
            return newComp;
        }


        public override void Draw(Graphics paper) //Draws all the gates
        {
            foreach (Gate g in compGates)
            {
                g.Draw(paper);
            }
        }
    }
}
