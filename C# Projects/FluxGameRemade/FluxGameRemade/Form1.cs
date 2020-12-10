using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace FluxGameRemade
{
    //Name: Kelvin David
    //ID: 1345360
    /// <summary>
    /// This is a programmed rendition of the 
    /// card game FLUXX
    /// </summary>
    public partial class Form1 : Form
    {
        Game newGame;
        List<string> HumanNames = new List<string>();
        Card selected;
        int humanPlayerCount = 0;

        public Form1()
        {
            InitializeComponent();
        }
        /// <summary>
        /// Starts Game
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonStartGame_Click(object sender, EventArgs e)
        {
            newGame = new Game();
            for (int i = 0; i < humanPlayerCount; i++)
            {
                Player newPlayer = new Player(HumanNames[i]);
                newGame.AddPlayer(newPlayer);
            }
            newGame.AddPlayer(new Computer());
            newGame.currentPlayer = newGame.playingList[0];
            MessageBox.Show("It is " + newGame.currentPlayer.Name + "'s " + " Turn");
            DisplayHand();
            DisplayBoard();
        }
        /// <summary>
        /// Adds a player to the game
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonAddPlayer_Click(object sender, EventArgs e)
        {
            if (textBoxName.Text == "")
            {
                MessageBox.Show("Please enter a name first");
            }
            else
            {
                humanPlayerCount += 1;
                HumanNames.Add(textBoxName.Text);
                MessageBox.Show(HumanNames[humanPlayerCount - 1] + " has joined the game :)");
                labelPlayer.Text = "Players: " + humanPlayerCount.ToString();
                textBoxName.Clear();
                textBoxName.Focus();
            }
        }
        /// <summary>
        /// Closes the program
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonExit_Click(object sender, EventArgs e)
        {
            this.Close();
        }
        /// <summary>
        /// Plays the selected card
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonPlaySelected_Click(object sender, EventArgs e)
        {
            if (newGame.CheckSpecfic("Play"))
            {
                MessageBox.Show("The max amount of plays have been made");
            }
            else
            {
                try
                {
                    int index = listBoxPlayerHand.SelectedIndex;
                    Card selected = newGame.currentPlayer.Hand[index];
                    newGame.PlayCard(selected);
                    newGame.playsRemain -= 1;
                    labelPlay.Text = "Plays Made: " + newGame.playsRemain;
                    newGame.plays += 1;
                    DisplayHand();
                    DisplayBoard();
                }
                catch
                {
                    MessageBox.Show("Please select a card to play");
                }
            }
        }
        /// <summary>
        /// Draws card and adds it to the hand
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonDrawCard_Click(object sender, EventArgs e)
        {
            if (newGame.CheckSpecfic("Draw"))
            {
                MessageBox.Show("The max amount of draws have been made");
            }
            else
            {
                newGame.currentPlayer.Hand.Add(newGame.DrawCard());
                newGame.drawsRemain -= 1;
                labelDraw.Text = "Draws Remain: " + newGame.drawsRemain;
                newGame.draws += 1;
                DisplayHand();
            }
        }
        /// <summary>
        /// This ends the turn and checks for winner and 
        /// moves on to next player
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonEndTurn_Click(object sender, EventArgs e)
        {
            if (newGame.CheckRules())
            {
                if (newGame.CheckWinner() != null)
                {
                    MessageBox.Show(newGame.CheckWinner().Name + " has won!!!");
                    this.Close();
                }
                newGame.NextTurn();
                DisplayHand();
                DisplayBoard();
            }
        }
        /// <summary>
        /// This discards the selected card
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonDiscard_Click(object sender, EventArgs e)
        {
            if (newGame.DiscardCheck() != null
                && !(newGame.CheckSpecfic(newGame.DiscardCheck())))
            {
                if (listBoxPlayerHand.SelectedIndex >= 0)
                {
                    newGame.DiscardCard(selected, false);
                }
                else if (listBoxPlayedItems.SelectedIndex > 0)
                {
                    if (newGame.currentPlayer.PlayedKeepers
                        .Contains(selected))
                    {
                        newGame.DiscardCard(selected, true);
                    }
                    else
                    {
                        MessageBox.Show("please choose a valid keeper");
                    }
                }
            }
            else
            {
                MessageBox.Show("You cannot discard a card at this time");
            }
            DisplayHand();
            DisplayBoard();
        }
        /// <summary>
        /// This displays the details of the selected card
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void listBoxPlayerHand_SelectedIndexChanged(object sender, EventArgs e)
        {
            try
            {
                int index = listBoxPlayerHand.SelectedIndex;
                selected = newGame.currentPlayer.Hand[index];
                MessageBox.Show(selected.Description);
            }
            catch
            {
                MessageBox.Show("Please select a card to play");
            }
        }
        /// <summary>
        /// Displays the current borad state
        /// </summary>
        public void DisplayBoard()
        {
            labelDraw.Text = "Draws Remain: " + newGame.drawsRemain;
            labelPlay.Text = "Plays Remain: " + newGame.playsRemain;
            listBoxPlayedItems.Items.Clear();
            DisplayGameInfo();
            labelPlayer.Text = newGame.currentPlayer.Name + "'s Turn";
            listBoxPlayedItems.Items.Add("=== KEEPERS IN PLAY ======");
            listBoxPlayedItems.Items.Add(" ");
            foreach (Player p in newGame.playingList)
            {
                listBoxPlayedItems.Items.Add("=== " + p.Name.ToUpper() + " ===");
                if (p.PlayedKeepers.Count == 0)
                {
                    listBoxPlayedItems.Items.Add("NONE");
                }
                foreach (Keeper k in p.PlayedKeepers)
                {
                    listBoxPlayedItems.Items.Add(k.ToString());
                }
                listBoxPlayedItems.Items.Add(" ");
            }
        }
        /// <summary>
        /// Displays the current hands state
        /// </summary>
        public void DisplayHand()
        {
            listBoxPlayerHand.Items.Clear();
            foreach (Card c in newGame.currentPlayer.Hand)
            {
                listBoxPlayerHand.Items.Add(c.ToString());
            }
        }
        /// <summary>
        /// Dispays the rules and goals
        /// </summary>
        public void DisplayGameInfo()
        {
            textBoxGoal.Clear();
            textBoxRules.Clear();
            if (newGame.currentGoal == null)
            {
                textBoxGoal.Text = "NO GOAL IN PLAY";
            }
            else
            {
                textBoxGoal.Text = newGame.currentGoal.Description;
            }
            foreach (Rule r in newGame.rulesInPlay)
            {
                textBoxRules.Text += r.Type.ToString().ToUpper() + ": " +
                    r.Description + "\r\n" + "\r\n";
            }
        }
        /// <summary>
        /// Finds the index of the selected keeper
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void listBoxPlayedItems_SelectedIndexChanged(object sender, EventArgs e)
        {
            int index = listBoxPlayedItems.SelectedIndex;
            try
            {
                foreach (Keeper k in newGame.currentPlayer.PlayedKeepers)
                {
                    if (k.ToString() == listBoxPlayedItems.Items[index].ToString())
                    {
                        index = (index - listBoxPlayedItems.Items.Count) + (index);
                        selected = newGame.currentPlayer.Hand[index];
                    }
                }
            }
            catch
            {
                MessageBox.Show("Please select a keeper");
            }
        }

        private void label4_Click(object sender, EventArgs e)
        {

        }
    }
}
