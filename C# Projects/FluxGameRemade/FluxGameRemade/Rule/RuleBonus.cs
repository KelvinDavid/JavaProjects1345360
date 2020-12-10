using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FluxGameRemade
{
    /// <summary>
    /// This is the subclass of rule that holds all cards
    /// that are bonus related
    /// </summary>
    class RuleBonus : Rule
    {
        private int Bonus;

        public RuleBonus(string type, int Knum)
        {
            Catagorey = "Bonus";
            _type = type;
            Bonus = 1;
        }

        public override bool Check(List<Player> playing,int draw, int play, int handLimit, int keeperLimit)
        {
            foreach (Player p in playing)
            {
                foreach (Keeper k in p.PlayedKeepers)
                {
                    if (Type == "'The Party'")
                    {
                        if (k.Type == KeeperType.TheParty)
                        {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public override void ErrorMessage()
        {
            //Do Nothing
        }

        public override string Description
        {
            get
            {
                return "If any player has " + Type + " Keeper" +
                    "Everyone may draw and play 1 more card " +
                    "each turn";
            }
        }

    }
}
