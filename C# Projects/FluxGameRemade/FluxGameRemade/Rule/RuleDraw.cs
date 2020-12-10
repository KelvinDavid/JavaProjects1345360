using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace FluxGameRemade
{
    /// <summary>
    /// This is a subclass of rule that holds all the
    /// draw related rules
    /// </summary>
    class RuleDraw : Rule
    {
        public int NDraws;

        public RuleDraw(string type, int numDraws)
        {
            Catagorey = "Draw";
            _type = type;
            NDraws = numDraws;
        }

        public override bool Check(List<Player> playing,int draw, int play, int handLimit, int keeperLimit)
        {
            if (Type == "Basic" && draw == NDraws)
            {
                return true;
            }
            else if (Type == "Draw 2" && draw == NDraws)
            {
                return true;
            }
            return false;
        }

        public override void ErrorMessage()
        {
            MessageBox.Show("The required amount of " +
                "draws have not been met. Please draw " +
                "more Cards");
        }
        public override string Description
        {
            get
            {
                return "All players must draw " + NDraws + " cards each turn";
            }
        }
    }
}
