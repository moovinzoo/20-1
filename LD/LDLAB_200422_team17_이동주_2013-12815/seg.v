`timescale 1ns / 1ps

module seg(
    input [3:0] in,
    output [7:0] out
    );

	seg7_display Seg7_display(
		.in(out)
	);
	// Implement your code from here.
	
	reg [7:0] klingon;
	assign out = klingon;
	
	always @(*)
		case (in)
		//	 in           	  	    klingon
		//   in3210                dgfedcba      input
			4'b0000:	klingon = 8'b00111111;// 0
			4'b0001:	klingon = 8'b00000001;// 1
			4'b0010:	klingon = 8'b01000001;// 2
			4'b0011:	klingon = 8'b01001001;// 3
			4'b0100:	klingon = 8'b01100010;// 4
			4'b0101:	klingon = 8'b01011100;// 5
			4'b0110:	klingon = 8'b01010010;// 6
			4'b0111:	klingon = 8'b01100100;// 7
			4'b1000:	klingon = 8'b00110110;// 8
			4'b1001:	klingon = 8'b01110110;// 9
			default:	klingon = 8'b00000000;// over 9
		endcase
	

endmodule
