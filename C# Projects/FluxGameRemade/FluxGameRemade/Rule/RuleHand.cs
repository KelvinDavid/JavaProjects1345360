using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace FluxGameRemade
{
    /// <summary>
    /// This is a subclass of rule that holds all
    /// the rules related to hand limits
    /// </summary>
    class RuleHand : Rule
    {
        private int NHLimit;

        public RuleHand(string type, int numHLimit)
        {
            Catagorey = "Hand";
            _type = type;
            NHLimit = numHLimit + 1;
        }

        public override bool Check(List<Player> playing, int draw, int play, int handLimit, int keeperLimit)
        {
            if (Type == "Hand Limit 2" && handLimit <= NHLimit)
            {
                return true;
            }
            else if (Type == "Hand Limit 3" && handLimit <= NHLimit)
            {
                return true;
            }
            return false;
        }

        public override void ErrorMessage()
        {
            MessageBox.Show("HAND LIMIT: " +
                "please choose a card to discard");
        }

        public override string Description
        {
            get
            {
                return "All players must have " + NHLimit + " Cards(MAX) in hand" +
                    "All other cards must be discarded";
            }
        }
    }
}
