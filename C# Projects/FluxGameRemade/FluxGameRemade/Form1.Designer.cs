namespace FluxGameRemade
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.listBoxPlayerHand = new System.Windows.Forms.ListBox();
            this.listBoxPlayedItems = new System.Windows.Forms.ListBox();
            this.buttonEndTurn = new System.Windows.Forms.Button();
            this.buttonDrawCard = new System.Windows.Forms.Button();
            this.buttonPlaySelected = new System.Windows.Forms.Button();
            this.buttonStartGame = new System.Windows.Forms.Button();
            this.buttonDiscard = new System.Windows.Forms.Button();
            this.textBoxRules = new System.Windows.Forms.TextBox();
            this.textBoxGoal = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.buttonAddPlayer = new System.Windows.Forms.Button();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.label6 = new System.Windows.Forms.Label();
            this.textBoxName = new System.Windows.Forms.TextBox();
            this.buttonExit = new System.Windows.Forms.Button();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.labelPlayer = new System.Windows.Forms.Label();
            this.labelPlay = new System.Windows.Forms.Label();
            this.labelDraw = new System.Windows.Forms.Label();
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.groupBox3.SuspendLayout();
            this.SuspendLayout();
            // 
            // listBoxPlayerHand
            // 
            this.listBoxPlayerHand.FormattingEnabled = true;
            this.listBoxPlayerHand.ItemHeight = 16;
            this.listBoxPlayerHand.Location = new System.Drawing.Point(11, 59);
            this.listBoxPlayerHand.Margin = new System.Windows.Forms.Padding(2);
            this.listBoxPlayerHand.Name = "listBoxPlayerHand";
            this.listBoxPlayerHand.Size = new System.Drawing.Size(185, 68);
            this.listBoxPlayerHand.TabIndex = 2;
            this.listBoxPlayerHand.SelectedIndexChanged += new System.EventHandler(this.listBoxPlayerHand_SelectedIndexChanged);
            // 
            // listBoxPlayedItems
            // 
            this.listBoxPlayedItems.FormattingEnabled = true;
            this.listBoxPlayedItems.ItemHeight = 16;
            this.listBoxPlayedItems.Location = new System.Drawing.Point(211, 59);
            this.listBoxPlayedItems.Margin = new System.Windows.Forms.Padding(2);
            this.listBoxPlayedItems.Name = "listBoxPlayedItems";
            this.listBoxPlayedItems.Size = new System.Drawing.Size(375, 132);
            this.listBoxPlayedItems.TabIndex = 25;
            this.listBoxPlayedItems.SelectedIndexChanged += new System.EventHandler(this.listBoxPlayedItems_SelectedIndexChanged);
            // 
            // buttonEndTurn
            // 
            this.buttonEndTurn.Location = new System.Drawing.Point(10, 100);
            this.buttonEndTurn.Margin = new System.Windows.Forms.Padding(2);
            this.buttonEndTurn.Name = "buttonEndTurn";
            this.buttonEndTurn.Size = new System.Drawing.Size(186, 28);
            this.buttonEndTurn.TabIndex = 28;
            this.buttonEndTurn.Text = "End Turn";
            this.buttonEndTurn.UseVisualStyleBackColor = true;
            this.buttonEndTurn.Click += new System.EventHandler(this.buttonEndTurn_Click);
            // 
            // buttonDrawCard
            // 
            this.buttonDrawCard.Location = new System.Drawing.Point(9, 68);
            this.buttonDrawCard.Margin = new System.Windows.Forms.Padding(2);
            this.buttonDrawCard.Name = "buttonDrawCard";
            this.buttonDrawCard.Size = new System.Drawing.Size(186, 28);
            this.buttonDrawCard.TabIndex = 27;
            this.buttonDrawCard.Text = "Draw Card";
            this.buttonDrawCard.UseVisualStyleBackColor = true;
            this.buttonDrawCard.Click += new System.EventHandler(this.buttonDrawCard_Click);
            // 
            // buttonPlaySelected
            // 
            this.buttonPlaySelected.Location = new System.Drawing.Point(9, 36);
            this.buttonPlaySelected.Margin = new System.Windows.Forms.Padding(2);
            this.buttonPlaySelected.Name = "buttonPlaySelected";
            this.buttonPlaySelected.Size = new System.Drawing.Size(186, 28);
            this.buttonPlaySelected.TabIndex = 26;
            this.buttonPlaySelected.Text = "Play Selected";
            this.buttonPlaySelected.UseVisualStyleBackColor = true;
            this.buttonPlaySelected.Click += new System.EventHandler(this.buttonPlaySelected_Click);
            // 
            // buttonStartGame
            // 
            this.buttonStartGame.Location = new System.Drawing.Point(5, 87);
            this.buttonStartGame.Margin = new System.Windows.Forms.Padding(2);
            this.buttonStartGame.Name = "buttonStartGame";
            this.buttonStartGame.Size = new System.Drawing.Size(186, 28);
            this.buttonStartGame.TabIndex = 30;
            this.buttonStartGame.Text = "Start Game";
            this.buttonStartGame.UseVisualStyleBackColor = true;
            this.buttonStartGame.Click += new System.EventHandler(this.buttonStartGame_Click);
            // 
            // buttonDiscard
            // 
            this.buttonDiscard.Location = new System.Drawing.Point(10, 23);
            this.buttonDiscard.Margin = new System.Windows.Forms.Padding(2);
            this.buttonDiscard.Name = "buttonDiscard";
            this.buttonDiscard.Size = new System.Drawing.Size(186, 28);
            this.buttonDiscard.TabIndex = 32;
            this.buttonDiscard.Text = "Discard Selected";
            this.buttonDiscard.UseVisualStyleBackColor = true;
            this.buttonDiscard.Click += new System.EventHandler(this.buttonDiscard_Click);
            // 
            // textBoxRules
            // 
            this.textBoxRules.Enabled = false;
            this.textBoxRules.Location = new System.Drawing.Point(211, 356);
            this.textBoxRules.Multiline = true;
            this.textBoxRules.Name = "textBoxRules";
            this.textBoxRules.Size = new System.Drawing.Size(374, 238);
            this.textBoxRules.TabIndex = 33;
            // 
            // textBoxGoal
            // 
            this.textBoxGoal.Enabled = false;
            this.textBoxGoal.Location = new System.Drawing.Point(212, 248);
            this.textBoxGoal.Multiline = true;
            this.textBoxGoal.Name = "textBoxGoal";
            this.textBoxGoal.Size = new System.Drawing.Size(374, 35);
            this.textBoxGoal.TabIndex = 34;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Bahnschrift Condensed", 19.875F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(37, 7);
            this.label2.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(128, 41);
            this.label2.TabIndex = 35;
            this.label2.Text = "Your Hand";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Bahnschrift Condensed", 19.875F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(366, 7);
            this.label3.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(84, 41);
            this.label3.TabIndex = 36;
            this.label3.Text = "Board";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Bahnschrift Condensed", 19.875F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(333, 200);
            this.label1.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(159, 41);
            this.label1.TabIndex = 37;
            this.label1.Text = "Current Goal";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Bahnschrift Condensed", 19.875F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label5.Location = new System.Drawing.Point(321, 298);
            this.label5.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(171, 41);
            this.label5.TabIndex = 38;
            this.label5.Text = "Current Rules";
            // 
            // buttonAddPlayer
            // 
            this.buttonAddPlayer.Location = new System.Drawing.Point(5, 55);
            this.buttonAddPlayer.Margin = new System.Windows.Forms.Padding(2);
            this.buttonAddPlayer.Name = "buttonAddPlayer";
            this.buttonAddPlayer.Size = new System.Drawing.Size(186, 28);
            this.buttonAddPlayer.TabIndex = 39;
            this.buttonAddPlayer.Text = "Add Player";
            this.buttonAddPlayer.UseVisualStyleBackColor = true;
            this.buttonAddPlayer.Click += new System.EventHandler(this.buttonAddPlayer_Click);
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.label6);
            this.groupBox1.Controls.Add(this.textBoxName);
            this.groupBox1.Controls.Add(this.buttonExit);
            this.groupBox1.Controls.Add(this.buttonAddPlayer);
            this.groupBox1.Controls.Add(this.buttonStartGame);
            this.groupBox1.Location = new System.Drawing.Point(6, 447);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(200, 162);
            this.groupBox1.TabIndex = 40;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Game Controls";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(8, 31);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(49, 17);
            this.label6.TabIndex = 44;
            this.label6.Text = "Name:";
            // 
            // textBoxName
            // 
            this.textBoxName.Location = new System.Drawing.Point(63, 28);
            this.textBoxName.Name = "textBoxName";
            this.textBoxName.Size = new System.Drawing.Size(128, 22);
            this.textBoxName.TabIndex = 44;
            // 
            // buttonExit
            // 
            this.buttonExit.Location = new System.Drawing.Point(5, 119);
            this.buttonExit.Margin = new System.Windows.Forms.Padding(2);
            this.buttonExit.Name = "buttonExit";
            this.buttonExit.Size = new System.Drawing.Size(186, 28);
            this.buttonExit.TabIndex = 40;
            this.buttonExit.Text = "Exit Game";
            this.buttonExit.UseVisualStyleBackColor = true;
            this.buttonExit.Click += new System.EventHandler(this.buttonExit_Click);
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.buttonPlaySelected);
            this.groupBox2.Controls.Add(this.buttonDrawCard);
            this.groupBox2.Controls.Add(this.buttonEndTurn);
            this.groupBox2.Location = new System.Drawing.Point(5, 294);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(200, 147);
            this.groupBox2.TabIndex = 41;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Player Controls";
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.buttonDiscard);
            this.groupBox3.Location = new System.Drawing.Point(5, 222);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(201, 66);
            this.groupBox3.TabIndex = 42;
            this.groupBox3.TabStop = false;
            this.groupBox3.Text = "Special Controls";
            // 
            // labelPlayer
            // 
            this.labelPlayer.AutoSize = true;
            this.labelPlayer.Font = new System.Drawing.Font("Bahnschrift Condensed", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelPlayer.Location = new System.Drawing.Point(50, 129);
            this.labelPlayer.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.labelPlayer.Name = "labelPlayer";
            this.labelPlayer.Size = new System.Drawing.Size(100, 24);
            this.labelPlayer.TabIndex = 43;
            this.labelPlayer.Text = "Players: None";
            // 
            // labelPlay
            // 
            this.labelPlay.AutoSize = true;
            this.labelPlay.Font = new System.Drawing.Font("Bahnschrift Condensed", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelPlay.Location = new System.Drawing.Point(10, 177);
            this.labelPlay.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.labelPlay.Name = "labelPlay";
            this.labelPlay.Size = new System.Drawing.Size(103, 24);
            this.labelPlay.TabIndex = 44;
            this.labelPlay.Text = "Plays Remain:";
            this.labelPlay.Click += new System.EventHandler(this.label4_Click);
            // 
            // labelDraw
            // 
            this.labelDraw.AutoSize = true;
            this.labelDraw.Font = new System.Drawing.Font("Bahnschrift Condensed", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelDraw.Location = new System.Drawing.Point(11, 153);
            this.labelDraw.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.labelDraw.Name = "labelDraw";
            this.labelDraw.Size = new System.Drawing.Size(107, 24);
            this.labelDraw.TabIndex = 45;
            this.labelDraw.Text = "Draws Remain:";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(603, 621);
            this.Controls.Add(this.labelDraw);
            this.Controls.Add(this.labelPlay);
            this.Controls.Add(this.labelPlayer);
            this.Controls.Add(this.groupBox3);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.textBoxGoal);
            this.Controls.Add(this.textBoxRules);
            this.Controls.Add(this.listBoxPlayedItems);
            this.Controls.Add(this.listBoxPlayerHand);
            this.MinimumSize = new System.Drawing.Size(621, 668);
            this.Name = "Form1";
            this.Text = "Form1";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.groupBox2.ResumeLayout(false);
            this.groupBox3.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox listBoxPlayerHand;
        private System.Windows.Forms.ListBox listBoxPlayedItems;
        private System.Windows.Forms.Button buttonEndTurn;
        private System.Windows.Forms.Button buttonDrawCard;
        private System.Windows.Forms.Button buttonPlaySelected;
        private System.Windows.Forms.Button buttonStartGame;
        private System.Windows.Forms.Button buttonDiscard;
        private System.Windows.Forms.TextBox textBoxRules;
        private System.Windows.Forms.TextBox textBoxGoal;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Button buttonAddPlayer;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.Label labelPlayer;
        private System.Windows.Forms.Button buttonExit;
        private System.Windows.Forms.TextBox textBoxName;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label labelPlay;
        private System.Windows.Forms.Label labelDraw;
    }
}

