`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    16:38:45 12/12/2017 
// Design Name: 
// Module Name:    SevenSegmentDecoder 
// Project Name: 
// Target Devices: 
// Tool versions: 
// Description: 
//
// Dependencies: 
//
// Revision: 
// Revision 0.01 - File Created
// Additional Comments: 
//
//////////////////////////////////////////////////////////////////////////////////
module SevenSegmentDecoder(
	input [3:0] Number,
	input DC,
   output reg [6:0] Display
	);

	always @(*) begin
		if(DC) begin
			Display <= 7'b1000000; // -
		end
		else begin
			case(Number)
				 8'd0: Display <= 7'b0111111; // 0
				 8'd1: Display <= 7'b0000110; // 1
				 8'd2: Display <= 7'b1011011; // 2
				 8'd3: Display <= 7'b1001111; // 3
				 8'd4: Display <= 7'b1100110; // 4
				 8'd5: Display <= 7'b1101101; // 5
				 8'd6: Display <= 7'b1111101; // 6
				 8'd7: Display <= 7'b0000111; // 7
				 8'd8: Display <= 7'b1111111; // 8
				 8'd9: Display <= 7'b1101111; // 9
				 8'd10: Display <= 7'b1110111; // A
				 8'd11: Display <= 7'b1111100; // b
				 8'd12: Display <= 7'b0111001; // C
				 8'd13: Display <= 7'b1011110; // d
				 8'd14: Display <= 7'b1111001; // E
				 8'd15: Display <= 7'b1110001; // F
				 default: Display <= 7'b0000000; // 0
			 endcase
		end
	end
	
endmodule
