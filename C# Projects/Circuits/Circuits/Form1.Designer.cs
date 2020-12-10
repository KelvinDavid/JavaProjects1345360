namespace Circuits
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
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.toolStrip1 = new System.Windows.Forms.ToolStrip();
            this.buttonAnd = new System.Windows.Forms.ToolStripButton();
            this.buttonOr = new System.Windows.Forms.ToolStripButton();
            this.buttonNot = new System.Windows.Forms.ToolStripButton();
            this.buttonPAD = new System.Windows.Forms.ToolStripButton();
            this.toolStripDropDownButtonInputOutput = new System.Windows.Forms.ToolStripDropDownButton();
            this.switchToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.lampToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripDropDownButtonTools = new System.Windows.Forms.ToolStripDropDownButton();
            this.copyToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.evaluateToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripDropDownButtonCompound = new System.Windows.Forms.ToolStripDropDownButton();
            this.startGroupToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.endGroupToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.imageListGates = new System.Windows.Forms.ImageList(this.components);
            this.toolStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // toolStrip1
            // 
            this.toolStrip1.BackColor = System.Drawing.SystemColors.ControlLight;
            this.toolStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.buttonAnd,
            this.buttonOr,
            this.buttonNot,
            this.buttonPAD,
            this.toolStripDropDownButtonInputOutput,
            this.toolStripDropDownButtonTools,
            this.toolStripDropDownButtonCompound});
            this.toolStrip1.Location = new System.Drawing.Point(0, 0);
            this.toolStrip1.Name = "toolStrip1";
            this.toolStrip1.Size = new System.Drawing.Size(472, 25);
            this.toolStrip1.TabIndex = 0;
            this.toolStrip1.Text = "toolStrip1";
            // 
            // buttonAnd
            // 
            this.buttonAnd.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.buttonAnd.Image = ((System.Drawing.Image)(resources.GetObject("buttonAnd.Image")));
            this.buttonAnd.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.buttonAnd.Name = "buttonAnd";
            this.buttonAnd.Size = new System.Drawing.Size(23, 22);
            this.buttonAnd.Text = "And";
            this.buttonAnd.Click += new System.EventHandler(this.buttonAnd_Click);
            // 
            // buttonOr
            // 
            this.buttonOr.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.buttonOr.Image = ((System.Drawing.Image)(resources.GetObject("buttonOr.Image")));
            this.buttonOr.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.buttonOr.Name = "buttonOr";
            this.buttonOr.Size = new System.Drawing.Size(23, 22);
            this.buttonOr.Text = "toolStripButton1";
            this.buttonOr.Click += new System.EventHandler(this.buttonOr_Click);
            // 
            // buttonNot
            // 
            this.buttonNot.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.buttonNot.Image = ((System.Drawing.Image)(resources.GetObject("buttonNot.Image")));
            this.buttonNot.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.buttonNot.Name = "buttonNot";
            this.buttonNot.Size = new System.Drawing.Size(23, 22);
            this.buttonNot.Text = "toolStripButton1";
            this.buttonNot.Click += new System.EventHandler(this.buttonNot_Click);
            // 
            // buttonPAD
            // 
            this.buttonPAD.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Text;
            this.buttonPAD.Image = ((System.Drawing.Image)(resources.GetObject("buttonPAD.Image")));
            this.buttonPAD.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.buttonPAD.Name = "buttonPAD";
            this.buttonPAD.Size = new System.Drawing.Size(33, 22);
            this.buttonPAD.Text = "PAD";
            this.buttonPAD.Click += new System.EventHandler(this.buttonPAD_Click);
            // 
            // toolStripDropDownButtonInputOutput
            // 
            this.toolStripDropDownButtonInputOutput.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Text;
            this.toolStripDropDownButtonInputOutput.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.switchToolStripMenuItem,
            this.lampToolStripMenuItem});
            this.toolStripDropDownButtonInputOutput.Image = ((System.Drawing.Image)(resources.GetObject("toolStripDropDownButtonInputOutput.Image")));
            this.toolStripDropDownButtonInputOutput.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.toolStripDropDownButtonInputOutput.Name = "toolStripDropDownButtonInputOutput";
            this.toolStripDropDownButtonInputOutput.Size = new System.Drawing.Size(87, 22);
            this.toolStripDropDownButtonInputOutput.Text = "Input/Ouput";
            // 
            // switchToolStripMenuItem
            // 
            this.switchToolStripMenuItem.Name = "switchToolStripMenuItem";
            this.switchToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.switchToolStripMenuItem.Text = "Switch";
            this.switchToolStripMenuItem.Click += new System.EventHandler(this.switchToolStripMenuItem_Click);
            // 
            // lampToolStripMenuItem
            // 
            this.lampToolStripMenuItem.Name = "lampToolStripMenuItem";
            this.lampToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.lampToolStripMenuItem.Text = "Lamp";
            this.lampToolStripMenuItem.Click += new System.EventHandler(this.lampToolStripMenuItem_Click);
            // 
            // toolStripDropDownButtonTools
            // 
            this.toolStripDropDownButtonTools.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Text;
            this.toolStripDropDownButtonTools.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.copyToolStripMenuItem,
            this.evaluateToolStripMenuItem});
            this.toolStripDropDownButtonTools.Image = ((System.Drawing.Image)(resources.GetObject("toolStripDropDownButtonTools.Image")));
            this.toolStripDropDownButtonTools.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.toolStripDropDownButtonTools.Name = "toolStripDropDownButtonTools";
            this.toolStripDropDownButtonTools.Size = new System.Drawing.Size(48, 22);
            this.toolStripDropDownButtonTools.Text = "Tools";
            // 
            // copyToolStripMenuItem
            // 
            this.copyToolStripMenuItem.Name = "copyToolStripMenuItem";
            this.copyToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.copyToolStripMenuItem.Text = "Copy ";
            this.copyToolStripMenuItem.Click += new System.EventHandler(this.copyToolStripMenuItem_Click);
            // 
            // evaluateToolStripMenuItem
            // 
            this.evaluateToolStripMenuItem.Name = "evaluateToolStripMenuItem";
            this.evaluateToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.evaluateToolStripMenuItem.Text = "Evaluate";
            this.evaluateToolStripMenuItem.Click += new System.EventHandler(this.evaluateToolStripMenuItem_Click);
            // 
            // toolStripDropDownButtonCompound
            // 
            this.toolStripDropDownButtonCompound.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Text;
            this.toolStripDropDownButtonCompound.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.startGroupToolStripMenuItem,
            this.endGroupToolStripMenuItem});
            this.toolStripDropDownButtonCompound.Image = ((System.Drawing.Image)(resources.GetObject("toolStripDropDownButtonCompound.Image")));
            this.toolStripDropDownButtonCompound.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.toolStripDropDownButtonCompound.Name = "toolStripDropDownButtonCompound";
            this.toolStripDropDownButtonCompound.Size = new System.Drawing.Size(112, 22);
            this.toolStripDropDownButtonCompound.Text = "Compound Tools";
            // 
            // startGroupToolStripMenuItem
            // 
            this.startGroupToolStripMenuItem.Name = "startGroupToolStripMenuItem";
            this.startGroupToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.startGroupToolStripMenuItem.Text = "Start Group";
            this.startGroupToolStripMenuItem.Click += new System.EventHandler(this.startGroupToolStripMenuItem_Click);
            // 
            // endGroupToolStripMenuItem
            // 
            this.endGroupToolStripMenuItem.Name = "endGroupToolStripMenuItem";
            this.endGroupToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.endGroupToolStripMenuItem.Text = "End Group";
            this.endGroupToolStripMenuItem.Click += new System.EventHandler(this.endGroupToolStripMenuItem_Click);
            // 
            // imageListGates
            // 
            this.imageListGates.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imageListGates.ImageStream")));
            this.imageListGates.TransparentColor = System.Drawing.Color.Transparent;
            this.imageListGates.Images.SetKeyName(0, "OrGate.png");
            this.imageListGates.Images.SetKeyName(1, "OrGateAllRed.png");
            this.imageListGates.Images.SetKeyName(2, "NotGate.png");
            this.imageListGates.Images.SetKeyName(3, "NotGateAllRed.png");
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.HotTrack;
            this.ClientSize = new System.Drawing.Size(472, 307);
            this.Controls.Add(this.toolStrip1);
            this.Name = "Form1";
            this.Text = "Digital Circuits 104";
            this.Paint += new System.Windows.Forms.PaintEventHandler(this.Form1_Paint);
            this.MouseClick += new System.Windows.Forms.MouseEventHandler(this.Form1_MouseClick);
            this.MouseDown += new System.Windows.Forms.MouseEventHandler(this.Form1_MouseDown);
            this.MouseMove += new System.Windows.Forms.MouseEventHandler(this.Form1_MouseMove);
            this.MouseUp += new System.Windows.Forms.MouseEventHandler(this.Form1_MouseUp);
            this.toolStrip1.ResumeLayout(false);
            this.toolStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ToolStrip toolStrip1;
        private System.Windows.Forms.ToolStripButton buttonAnd;
        private System.Windows.Forms.ToolStripButton buttonOr;
        private System.Windows.Forms.ToolStripButton buttonNot;
        private System.Windows.Forms.ToolStripButton buttonPAD;
        private System.Windows.Forms.ImageList imageListGates;
        private System.Windows.Forms.ToolStripDropDownButton toolStripDropDownButtonInputOutput;
        private System.Windows.Forms.ToolStripMenuItem switchToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem lampToolStripMenuItem;
        private System.Windows.Forms.ToolStripDropDownButton toolStripDropDownButtonTools;
        private System.Windows.Forms.ToolStripMenuItem copyToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem evaluateToolStripMenuItem;
        private System.Windows.Forms.ToolStripDropDownButton toolStripDropDownButtonCompound;
        private System.Windows.Forms.ToolStripMenuItem startGroupToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem endGroupToolStripMenuItem;
    }
}

