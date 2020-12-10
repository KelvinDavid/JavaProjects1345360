using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FluxGameRemade
{
    /// <summary>
    /// this type holds all the keeper card
    /// types
    /// </summary>
    public enum KeeperType
    {
        Milk,
        Cookies,
        TheParty,
        Time,
        Sleep
    }
    /// <summary>
    /// This class holds all the info of the keeper types
    /// </summary>
    class Keeper : Card
    {
        public KeeperType Type;

        public Keeper(KeeperType type)
        {
            Type = type;
        }

        public override string ToString()
        {
            return Type.ToString();
        }
        public override string Description
        { get
            {
                return "Keeper: " + "An item you play to meet" +
                    " the current win" +" conditions";
            }
        }

    }
}
