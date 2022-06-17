namespace UniversitiesManagementSystem
{
    partial class AgenciesForm
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
            this.dgvAgency = new System.Windows.Forms.DataGridView();
            this.dgvBuildings = new System.Windows.Forms.DataGridView();
            this.connectButton = new System.Windows.Forms.Button();
            this.updateButton = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dgvAgency)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgvBuildings)).BeginInit();
            this.SuspendLayout();
            // 
            // dgvAgency
            // 
            this.dgvAgency.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvAgency.Location = new System.Drawing.Point(26, 28);
            this.dgvAgency.Name = "dgvAgency";
            this.dgvAgency.Size = new System.Drawing.Size(423, 154);
            this.dgvAgency.TabIndex = 0;
            // 
            // dgvBuildings
            // 
            this.dgvBuildings.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvBuildings.Location = new System.Drawing.Point(26, 235);
            this.dgvBuildings.Name = "dgvBuildings";
            this.dgvBuildings.Size = new System.Drawing.Size(423, 154);
            this.dgvBuildings.TabIndex = 1;
            // 
            // connectButton
            // 
            this.connectButton.Location = new System.Drawing.Point(572, 86);
            this.connectButton.Name = "connectButton";
            this.connectButton.Size = new System.Drawing.Size(116, 42);
            this.connectButton.TabIndex = 2;
            this.connectButton.Text = "Connect";
            this.connectButton.UseVisualStyleBackColor = true;
            this.connectButton.Click += new System.EventHandler(this.connectButton_Click);
            // 
            // updateButton
            // 
            this.updateButton.Location = new System.Drawing.Point(572, 286);
            this.updateButton.Name = "updateButton";
            this.updateButton.Size = new System.Drawing.Size(116, 40);
            this.updateButton.TabIndex = 3;
            this.updateButton.Text = "Update";
            this.updateButton.UseVisualStyleBackColor = true;
            this.updateButton.Click += new System.EventHandler(this.updateButton_Click);
            // 
            // UniversitiesForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.updateButton);
            this.Controls.Add(this.connectButton);
            this.Controls.Add(this.dgvBuildings);
            this.Controls.Add(this.dgvAgency);
            this.Name = "UniversitiesForm";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.dgvAgency)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgvBuildings)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dgvAgency;
        private System.Windows.Forms.DataGridView dgvBuildings;
        private System.Windows.Forms.Button connectButton;
        private System.Windows.Forms.Button updateButton;
    }
}

