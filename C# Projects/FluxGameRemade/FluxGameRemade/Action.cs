using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FluxGameRemade
{
    /// <summary>
    /// This enum type holds all the types
    /// of action cards
    /// </summary>
    public enum ActionType
    {
        EverybodyGets1,
        NoLimits,
        Draw2UseEm,
        RulesReset,
        TakeAnotherTurn
    }
    /// <summary>
    /// This class is or all thea ction cards in the game
    /// </summary>
    class Action : Card
    {
        public ActionType Type;
        public Action(ActionType type)
        {
            Type = type;
        }
        /// <summary>
        /// This method is called when a action card
        /// calls to remove rules from the currently played
        /// </summary>
        /// <param name="rulesInPlay"></param>
        /// <returns></returns>
        public List<Rule> ActionRule(List<Rule> rulesInPlay)
        {
            List<Rule> discard = new List<Rule>();
            if (Type == ActionType.NoLimits)
            {
                foreach (Rule r in rulesInPlay)
                {
                    if (r is RuleKeeper || r is RuleHand)
                    {
                        discard.Add(r);
                    }
                }
            }
            if (Type == ActionType.RulesReset)
            {
                foreach (Rule r in rulesInPlay)
                {
                    discard.Add(r);
                }
            }
            return discard;
        } 
        /// <summary>
        /// This overrides the ToString to reutrn the cards name
        /// </summary>
        /// <returns></returns>
        public override string ToString()
        {
            if (Type == ActionType.EverybodyGets1)
            {
                return "Everybody Gets 1";
            }
            else if (Type == ActionType.NoLimits)
            {
                return "No Limits";
            }
            else if (Type == ActionType.Draw2UseEm)
            {
                return "Draw 2 Use'Em";
            }
            else if (Type == ActionType.RulesReset)
            {
                return "Rules Reset";
            }
            else if (Type == ActionType.TakeAnotherTurn)
            {
                return "Take Another Turn";
            }
            return "Error";
        }

        public override string Description
        {
            get
            {
                if (Type == ActionType.EverybodyGets1)
                {
                    return "All players are given one card from the deck";
                }
                else if (Type == ActionType.NoLimits)
                {
                    return "All limit related rules are discarded";
                }
                else if (Type == ActionType.Draw2UseEm)
                {
                    return "Automaically draws and plays 2 cards";
                }
                else if (Type == ActionType.RulesReset)
                {
                    return "All rules will be discarded and return to basic rules";
                }
                else if (Type == ActionType.TakeAnotherTurn)
                {
                    return "If played your turn repeats on next turn";
                }
                return "Error";
            }
        }
    }
}
