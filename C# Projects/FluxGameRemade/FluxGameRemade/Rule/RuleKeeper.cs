using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace FluxGameRemade
{
    /// <summary>
    /// This is a subclass of rule that holds
    /// all the keeper limit related rules
    /// </summary>
    class RuleKeeper : Rule
    {
        private int KLimit;

        public RuleKeeper(string type, int Knum)
        {
            Catagorey = "Keeper";
            _type = type;
            KLimit = Knum + 1;
        }

        public override bool Check(List<Player> playing, int draw, int play, int handLimit, int keeperLimit)
        {
            if (Type == "Keeper Limit 2" && keeperLimit <= KLimit)
            {
                return true;
            }
            else if (Type == "Keeper Limit 3" && keeperLimit <= KLimit)
            {
                return true;
            }
            return false;
        }

        public override void ErrorMessage()
        {
            MessageBox.Show("KEEPER LIMIT: " +
                "please choose a keeper to discard");
        }

        public override string Description
        {
            get
            {
                return "All players must have " + KLimit + " Keepers(MAX) " +
                    "All other Keepers must be discarded";
            }
        }
    }
}
