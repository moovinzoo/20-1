module dflipflop(
    input D,
    input CLK,
    output reg Q,
	 output reg Q_L
    );

	always @(negedge CLK) begin
	
		Q<=D;
		Q_L<= ~D;
		end
		

endmodule

