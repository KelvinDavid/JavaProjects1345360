using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FluxGameRemade
{
    /// <summary>
    /// This is the superclass for all the rule cards
    /// </summary>
    abstract class Rule : Card
    {
        /// <summary>
        /// This holds the current name of the card
        /// </summary>
        protected string _type;
        /// <summary>
        /// This is what catagorey the rule card belongs to...
        /// (i.e. Bouns, Draw, Play etc...)
        /// </summary>
        public string Catagorey;
        /// <summary>
        /// This gets the description of the current rule
        /// </summary>
        public override string Description { get; }
        /// <summary>
        /// This gets the name of the card of the current rule
        /// </summary>
        public virtual string Type { get { return _type; } }
        /// <summary>
        /// This is the method that checks for if the rule has been broken
        /// or not (or at least met)
        /// </summary>
        /// <param name="playing">This is holds all the players to check for hands and keepers</param>
        /// <param name="draw">This is the amount of draws the current player has made</param>
        /// <param name="play">This is the number of plats mmade by current player</param>
        /// <param name="handLimit">Checks hand count if hit limit</param>
        /// <param name="keeperLimit">Checks keeper count if hit limit</param>
        /// <returns></returns>
        public abstract bool Check(List<Player> playing, int draw, int play, int handLimit,
            int keeperLimit);
        /// <summary>
        /// This holds the error message for each rule if not met
        /// </summary>
        public abstract void ErrorMessage();
        /// <summary>
        /// This checks for contradictions when adding rules to the rules in play
        /// </summary>
        /// <param name="playingRules"></param>
        /// <returns></returns>
        public virtual bool CheckContradiction(List<Rule> playingRules)
        {
            foreach (Rule r in playingRules)
            {
                if (r.Catagorey == Catagorey)
                {
                    return true;
                }
            }
            return false;
        }
        /// <summary>
        /// This replaces the contradited rule with the rule being currently added
        /// </summary>
        /// <param name="playingRules"></param>
        /// <returns></returns>
        public virtual Rule ReplaceIndex(List<Rule> playingRules)
        {
            foreach (Rule r in playingRules)
            {
                if (r.Catagorey == Catagorey)
                {
                    return r;
                }
            }
            return null;
        }
        /// <summary>
        /// This overrides the ToString method to return card name
        /// </summary>
        /// <returns></returns>
        public override string ToString()
        {
            return Type;
        }
    }
}
