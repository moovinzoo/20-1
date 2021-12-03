`timescale 1ns / 1ps
module seg_tb;
	reg [3:0] in;
	wire [7:0] out;
	seg uut (
		.in(in), 
		.out(out)
	);

	initial begin
		// Initialize Inputs
		in = 4'b0000;
		#50;
		in = 4'b0001;
		#50;
		in = 4'b0010;
		#50;
		in = 4'b0011;
		#50;
		in = 4'b0100;
		#50;
		in = 4'b0101;
		#50;
		in = 4'b0110;
		#50;
		in = 4'b0111;
		#50;
		in = 4'b1000;
		#50;
		in = 4'b1001;
		#50;
		in = 4'b1010;
		#50;
		in = 4'b1011;
		#50;
		in = 4'b1100;
		#50;
		in = 4'b1101;
		#50;
		in = 4'b1110;
		#50;
		in = 4'b1111;
		#50;
	end
      
endmodule

