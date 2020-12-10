using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FluxGameRemade
{
    /// <summary>
    /// This is the super class of all cards in the game
    /// </summary>
    abstract class Card
    {
        /// <summary>
        /// This gets the description of the currrent card
        /// </summary>
        public abstract string Description { get; }
        /// <summary>
        /// This overrides the tostring method to return the card name
        /// </summary>
        /// <returns></returns>
        public abstract override string ToString();
    }
}
