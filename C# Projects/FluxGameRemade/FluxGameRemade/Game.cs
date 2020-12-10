using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace FluxGameRemade
{
    /// <summary>
    /// This class holds all the game informaiton and functions.
    /// each new game object is a new game
    /// </summary>
    class Game
    {
        private Random rand = new Random();
        /// <summary>
        /// This Lists hold all the different cards
        /// </summary>
        private List<Card> deckList = new List<Card>();
        private List<Card> discardList = new List<Card>();
        private List<Card> basicRules = new List<Card>();
        private RuleType RuleLists = new RuleType();
        /// <summary>
        /// Theses lists hold the current players, rulels and goals
        /// </summary>
        public Player currentPlayer;
        public List<Player> playingList = new List<Player>();
        public List<Rule> rulesInPlay = new List<Rule>();
        public Goal currentGoal = null;
        /// <summary>
        /// These variables keep track of the actions
        /// </summary>
        private bool TakeAnotherTurn = false;
        public int bonus;
        public int draws;
        public int plays;
        public int drawsRemain;
        public int playsRemain;
        /// <summary>
        /// This is the consructor of the game class
        /// it creates the whole deck with all the needed cards
        /// </summary>
        public Game()
        {
            foreach (ActionType t in Enum.GetValues(typeof(ActionType)))
            {
                deckList.Add(new Action(t));
            }
            foreach (KeeperType t in Enum.GetValues(typeof(KeeperType)))
            {
                deckList.Add(new Keeper(t));
            }
            foreach (GoalType t in Enum.GetValues(typeof(GoalType)))
            {
                deckList.Add(new Goal(t));
            }
            for (int i = 0; i < RuleLists.BonusType.Count;i++)
            {
                deckList.Add(new RuleBonus(RuleLists.BonusType[i], i+1));
            }
            for (int i = 0; i < RuleLists.DrawType.Count; i++)
            {
                if (RuleLists.DrawType[i] == "Basic")
                {
                    rulesInPlay.Add(new RuleDraw(RuleLists.DrawType[i], i + 1));
                }
                else
                {
                    deckList.Add(new RuleDraw(RuleLists.DrawType[i], i + 1));
                }
            }
            for (int i = 0; i < RuleLists.HandType.Count; i++)
            {
                deckList.Add(new RuleHand(RuleLists.HandType[i], i + 1));
            }
            for (int i = 0; i < RuleLists.KLimitType.Count; i++)
            {
                deckList.Add(new RuleKeeper(RuleLists.KLimitType[i], i + 1));
            }
            for (int i = 0; i < RuleLists.PlayType.Count; i++)
            {
                if (RuleLists.DrawType[i] == "Basic")
                {
                    rulesInPlay.Add(new RulePlay(RuleLists.DrawType[i], i + 1));
                }
                else
                {
                    deckList.Add(new RulePlay(RuleLists.PlayType[i], i + 1));
                }
            }
            Shuffle();
            foreach (Rule r in rulesInPlay)
            {
                if (r is RuleDraw)
                {
                    RuleDraw refer = (RuleDraw)r;
                }
                if (r is RulePlay)
                {
                    RulePlay refer = (RulePlay)r;
                    playsRemain = refer.NPlays;
                }
            }
        }
        /// <summary>
        /// Checks the win conditions to declare winner
        /// </summary>
        /// <returns></returns>
        public Player CheckWinner()
        {
            foreach (Player p in playingList)
            {
                if (currentGoal != null)
                {
                    if (currentGoal.CheckWinCondition(p))
                    {
                        return p;
                    }
                }
            }
            return null;
        }
        /// <summary>
        /// Moves the game onto the next turn
        /// </summary>
        public void NextTurn()
        {
            foreach (Rule r in rulesInPlay)
            {
                if (r is RuleDraw)
                {
                    RuleDraw refer = (RuleDraw)r;
                    drawsRemain = refer.NDraws;
                }
                if (r is RulePlay)
                {
                    RulePlay refer = (RulePlay)r;
                    playsRemain = refer.NPlays;
                }
            }
            draws = plays = 0;
            if (TakeAnotherTurn == true)
            {
                TakeAnotherTurn = false;
            }
            else
            {
                int index = playingList.IndexOf(currentPlayer) + 1;
                if (index == playingList.Count)
                {
                    index = 0;
                }
                currentPlayer = playingList[index];
            }
            MessageBox.Show("It is " + currentPlayer.Name + "'s " + " Turn");
            if (currentPlayer is Computer)
            {
                RandomBot();
            }
        }
        /// <summary>
        /// Checks ther are draw cards or play cards in play if 
        /// not replace with 
        /// </summary>
        public void BasicRulesOverride()
        {
            bool playExists = false;
            bool drawExists = false;
            foreach (Rule r in rulesInPlay)
            {
                if (r.Catagorey == "Play")
                {
                    playExists = true;
                }
                if (r.Catagorey == "Draw")
                {
                    drawExists = true;
                }
            }
            foreach (Rule basic in basicRules)
            {
                if (basic.Catagorey == "Play" && playExists == false)
                {
                    rulesInPlay.Add(basic);
                }
                if (basic.Catagorey == "Draw" && drawExists == false)
                {
                    rulesInPlay.Add(basic);
                }
            }
        }

        /// <summary>
        /// Adds a player to the game
        /// </summary>
        /// <param name="player"></param>
        public void AddPlayer(Player player)
        {
            playingList.Add(player);
            for (int i = 1; i <= 3; i++)
            {
                player.AddHand(DrawCard());
            }
        }
        /// <summary>
        /// Adds the seleced rule into the rules list
        /// it first checks if there is contradtictions
        /// </summary>
        /// <param name="rulesInPlay">Rules list</param>
        /// <param name="refer">Chosen rule</param>
        private void AddRule(Rule rule)
        {
            if (rule.CheckContradiction(rulesInPlay))
            {
                if (rule.Type == "Basic")
                {
                    basicRules.Add(rule.ReplaceIndex(rulesInPlay));
                }
                else
                {
                    discardList.Add(rule.ReplaceIndex(rulesInPlay));
                }
                rulesInPlay.Remove(rule.ReplaceIndex(rulesInPlay));
            }
            rulesInPlay.Add(rule);
        }
        /// <summary>
        /// Draws a card from deck and refreshes the deck 
        /// by reshuffling and replacing it with the discarded
        /// </summary>
        /// <returns>The chosen card from deck</returns>
        public Card DrawCard()
        {
            if (deckList.Count == 0)
            {
                foreach (Card c in discardList)
                {
                    deckList.Add(c);
                    discardList.Remove(c);
                }
                Shuffle();
            }
            Card drawed = deckList[rand.Next(0, deckList.Count)];
            deckList.Remove(drawed);
            return drawed;
        }
        /// <summary>
        /// Discards the selected card
        /// </summary>
        /// <param name="selected">the selected card</param>
        /// <param name="keeper">if the selected is keeper</param>
        public void DiscardCard(Card selected, bool keeper)
        {
            if (keeper == true)
            {
                discardList.Add(selected);
                currentPlayer.PlayedKeepers.Remove(selected);
            }
            else
            {
                discardList.Add(selected);
                currentPlayer.Hand.Remove(selected);
            }
        }

        /// <summary>
        /// Shuffles the current deck
        /// </summary>
        public void Shuffle()
        {
            deckList.OrderBy(x => rand.Next(deckList.Count)).ToList();
        }
        /// <summary>
        /// Plays the selected card from hand
        /// </summary>
        /// <param name="selected">Selected card from hand</param>
        public void PlayCard(Card selected)
        {
            if (selected is Action)
            {
                ExecuteAction((Action)selected);
            }
            if (selected is Rule)
            {
                AddRule((Rule)selected);
            }
            if (selected is Goal)
            {
                currentGoal = (Goal)selected;
                discardList.Add(selected);
            }
            if (selected is Keeper)
            {
                currentPlayer.PlayedKeepers.Add((Keeper)selected);
            }
            currentPlayer.Hand.Remove(selected);
        }
        /// <summary>
        /// This method executes the selected 
        /// action card
        /// </summary>
        /// <param name="selected">selected card</param>
        public void ExecuteAction(Action selected)
        {
            if (selected.Type == ActionType.NoLimits ||
                selected.Type == ActionType.RulesReset)
            {
                List<Rule> ToDiscard = selected.ActionRule(rulesInPlay);
                foreach (Rule r in ToDiscard)
                {
                    if (r.Type == "Basic")
                    {
                        basicRules.Add(r);
                    }
                    else
                    {
                        discardList.Add(r);
                    }
                    rulesInPlay.Remove(r);
                }
                BasicRulesOverride();
            }
            else if (selected.Type == ActionType.EverybodyGets1)
            {
                foreach (Player p in playingList)
                {
                    p.AddHand(DrawCard());
                }
            }
            else if (selected.Type == ActionType.TakeAnotherTurn)
            {
                MessageBox.Show(currentPlayer.Name + " will take next turn again");
                TakeAnotherTurn = true;
            }
            else if (selected.Type == ActionType.Draw2UseEm)
            {
                for (int i = 0; i < 2; i++)
                {
                    PlayCard(DrawCard());
                }
            }
        }

        /// <summary>
        /// Checks if all rules have been met
        /// </summary>
        /// <returns></returns>
        public bool CheckRules()    
        {
            foreach (Rule r in rulesInPlay)
            {
                if (!(r.Check(playingList, draws, plays,
                    currentPlayer.Hand.Count,
                    currentPlayer.PlayedKeepers.Count)))
                {
                    r.ErrorMessage();
                    return false;
                }
                if (r.Catagorey == "Bonus")
                {
                    plays -= 1;
                }
                
            }
            return true;
        }
        /// <summary>
        /// Checks the specific rule card
        /// </summary>
        /// <param name="looking">the specific rule type</param>
        /// <returns></returns>
        public bool CheckSpecfic(string looking)
        {
            foreach (Rule r in rulesInPlay)
            {
                if (r is RuleDraw && looking == "Draw")
                {
                    if (r.Check(playingList, draws, plays,
                    currentPlayer.Hand.Count,
                    currentPlayer.PlayedKeepers.Count))
                    {
                        return true;
                    }
                }
                if (r is RulePlay && looking == "Play")
                {
                    if (r.Check(playingList, draws, plays,
                    currentPlayer.Hand.Count,
                    currentPlayer.PlayedKeepers.Count))
                    {
                        return true;
                    }
                }
                if (r is RuleHand && looking == "Hand")
                {
                    if (r.Check(playingList, draws, plays,
                    currentPlayer.Hand.Count,
                    currentPlayer.PlayedKeepers.Count))
                    {
                        return true;
                    }
                }
                if (r is RuleDraw && looking == "Keeper")
                {
                    if (r.Check(playingList, draws, plays,
                    currentPlayer.Hand.Count,
                    currentPlayer.PlayedKeepers.Count))
                    {
                        return true;
                    }
                }
            }
            return false;
        }
        /// <summary>
        /// This Checks whether discarding cards is allowed
        /// </summary>
        /// <returns></returns>
        public string DiscardCheck()
        {
            foreach (Rule r in rulesInPlay)
            {
                if (r.Catagorey == "Keeper")
                {
                    return r.Catagorey;
                }
                if (r.Catagorey == "Hand")
                {
                    return r.Catagorey;
                }
            }
            return null;
        }

        /// <summary>
        /// This is the simple programming
        /// for the random computer bot
        /// </summary>
        public void RandomBot()
        {
            for (int i = 0; i < rulesInPlay.Count; i++)
            {
                if (rulesInPlay[i].Catagorey == "Draw")
                {
                    while (CheckSpecfic("Draw") == false)
                    {
                        draws += 1;
                        currentPlayer.AddHand(DrawCard());
                    }
                }
                if (rulesInPlay[i].Catagorey == "Hand")
                {
                    while (CheckSpecfic("Hand") == false)
                    {
                        DiscardCard(currentPlayer.Hand[rand.Next(0, currentPlayer.Hand.Count)], false);
                    }
                }
                if (rulesInPlay[i].Catagorey == "Keeper")
                {
                    while (CheckSpecfic("Keeper") == false)
                    {
                        DiscardCard(currentPlayer.Hand[rand.Next(0, currentPlayer.PlayedKeepers.Count)], true);
                    }
                }
                if (rulesInPlay[i].Catagorey == "Play")
                {
                    while (CheckSpecfic("Play") == false)
                    {
                        plays += 1;
                        PlayCard(currentPlayer.Hand[rand.Next(0, currentPlayer.Hand.Count)]);
                    }
                }
            }
            NextTurn();
        }
    }
}
