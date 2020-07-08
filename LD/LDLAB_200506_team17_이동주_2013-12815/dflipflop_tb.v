module dflipflop_tb;

	// Inputs
	reg D;
	reg CLK;

	// Outputs
	wire Q;
	wire Q_L;

	// Instantiate the Unit Under Test (UUT)
	dflipflop uut (
		.D(D),
		.CLK(CLK),
		.Q(Q),
		.Q_L(Q_L)
	);
	initial begin
        CLK <= 1; // Initially 1
		D <= 0;
		#500;

		D <= 1;
		#500;

		D <= 0;
		#500;

		D <= 1;
	end

	always #100 CLK = ~CLK;
      
endmodule


