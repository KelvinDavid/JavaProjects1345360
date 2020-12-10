using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FluxGameRemade
{
    /// <summary>
    /// This type holds all the goal cards type
    /// </summary>
    enum GoalType
    {
        MilkAndCookies,
        PartySnacks,
        PartyTime,
        BedTime,
        FiveKeepers,
    }
    /// <summary>
    /// This class is all he goal cards in the game
    /// </summary>
    class Goal : Card
    {
        GoalType Type;
        public Goal(GoalType type)
        {
            Type = type;
        }
        /// <summary>
        /// This method goes through all the players in the game
        /// and checks if anyone has one or not
        /// </summary>
        /// <param name="player"></param>
        /// <returns></returns>
        public bool CheckWinCondition(Player player)
        {
            List<KeeperType> playedKeepers = new List<KeeperType>();
            foreach (Card c in player.PlayedKeepers)
            {
                if (c is Keeper)
                {
                    Keeper refer = (Keeper)c;
                    playedKeepers.Add(refer.Type);
                }
            }

            if (Type == GoalType.MilkAndCookies &&
                playedKeepers.Contains(KeeperType.Milk) &&
                playedKeepers.Contains(KeeperType.Cookies))
            {
                return true;
            }
            else if (Type == GoalType.PartySnacks &&
                playedKeepers.Contains(KeeperType.TheParty) &&
                playedKeepers.Contains(KeeperType.Cookies))
            {
                return true;
            }
            else if (Type == GoalType.BedTime &&
                playedKeepers.Contains(KeeperType.Sleep) &&
                playedKeepers.Contains(KeeperType.Time))
            {
                return true;
            }
            else if (Type == GoalType.PartyTime &&
                playedKeepers.Contains(KeeperType.Time) &&
                playedKeepers.Contains(KeeperType.TheParty))
            {

            }
            else if (Type == GoalType.FiveKeepers &&
                playedKeepers.Count >= 5)
            {
                return true;
            }
            return false;
        }

        public override string ToString()
        {
            if (Type == GoalType.BedTime)
            {
                return "Bed Time";
            }
            else if (Type == GoalType.FiveKeepers)
            {
                return "Five Keepers";
            }
            else if (Type == GoalType.MilkAndCookies)
            {
                return "Milk & Cookies";
            }
            else if (Type == GoalType.PartySnacks)
            {
                return "Party Snacks";
            }
            else if (Type == GoalType.PartyTime)
            {
                return "Party Time";
            }
            return "Error";
        }
        public override string Description
        { get
            {
                if (Type == GoalType.BedTime)
                {
                    return "If any player has both  the 'Sleep' & " +
                        "'Time' Keepers in play then they win";
                }
                else if (Type == GoalType.FiveKeepers)
                {
                    return "If any player has 5 or more Keepers " +
                        "then they win";
                }
                else if (Type == GoalType.MilkAndCookies)
                {
                    return "If any player has both the 'Milk' & " +
                        "'Cookies' Keepers in play then they win";
                }
                else if (Type == GoalType.PartySnacks)
                {
                    return "If any player has both the 'Cookies' & " +
                        "'The Party' Keeper in play then they win";
                }
                else if (Type == GoalType.PartyTime)
                {
                    return "If any player has both the 'Time' & " +
                        "'The Party' Keeper in play then they win";
                }
                return "Error";
            }
        }
    }
}
