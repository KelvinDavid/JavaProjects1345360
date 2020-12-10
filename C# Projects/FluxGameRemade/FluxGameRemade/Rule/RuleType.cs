using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FluxGameRemade
{
    /// <summary>
    /// This class holds all the types of rules
    /// </summary>
    class RuleType
    {
        /// <summary>
        /// This is all the types of rule bonuses
        /// </summary>
        public List<string> BonusType = new List<string>()
        {
            "'The Party'",
        };
        /// <summary>
        /// This is all the types of rule draws
        /// </summary>
        public List<string> DrawType = new List<string>()
        {
            "Basic",
            "Draw 2"
        };
        /// <summary>
        /// This is all the hand limit cards
        /// </summary>
        public List<string> HandType = new List<string>()
        {
            "Hand Limit 2",
            "Hand Limit 3"
        };
        /// <summary>
        /// THis is all the keeper limit types
        /// </summary>
        public List<string> KLimitType = new List<string>()
        {
            "Keeper Limit 2",
            "Keeper Limit 3"
        };
        /// <summary>
        /// This holds all the play related rules
        /// </summary>
        public List<string> PlayType = new List<string>()
        {
            "Basic",
            "Play 2"
        };

    }
}
