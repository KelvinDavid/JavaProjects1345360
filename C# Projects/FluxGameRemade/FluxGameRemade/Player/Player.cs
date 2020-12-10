using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FluxGameRemade
{
    /// <summary>
    /// This class is a player in the game
    /// </summary>
    class Player
    {
        private string _name;
        /// <summary>
        /// This is holds all the cards in the players hand
        /// </summary>
        protected List<Card> hand = new List<Card>();
        /// <summary>
        /// This list holds all the playedkeepers that belong to this player
        /// </summary>
        protected List<Card> playedKeepers = new List<Card>();

        public Player(string name)
        {
            _name = name;
        }
        /// <summary>
        /// This gets the name of the player
        /// </summary>
        public string Name { get { return _name; } }
        /// <summary>
        /// This gets the list of cards in hand
        /// </summary>
        public virtual List<Card> Hand { get { return hand; } set { hand = value; } }
        /// <summary>
        /// This gets the list of played keepers this player has
        /// </summary>
        public virtual List<Card> PlayedKeepers { get { return playedKeepers; } set { playedKeepers = value; } }
        /// <summary>
        /// This adds a card to the players hand
        /// </summary>
        /// <param name="card"></param>
        public virtual void AddHand(Card card)
        {
            Hand.Add(card);
        }
    }
}
