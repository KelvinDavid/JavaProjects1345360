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
    /// all rules related with plays
    /// </summary>
    class RulePlay : Rule
    {
        public int NPlays;

        public RulePlay(string type, int numPlays)
        {
            Catagorey = "Play";
            _type = type;
            NPlays = numPlays;
        }


        public override bool Check(List<Player> playing, int draw, int play, int handLimit, int keeperLimit)
        {
            if (Type == "Basic" && play == NPlays)
            {
                return true;
            }
            else if (Type == "Play 2" && play == NPlays)
            {
                return true;
            }
            return false;
        }

        public override void ErrorMessage()
        {
            MessageBox.Show("The required amount" +
                " of plays have not been met. " +
                "Please play more cards");
        }

        public override string Description
        {
            get
            {
                return "All players must play " + NPlays + " cards each turn";
            }
        }
    }
}
