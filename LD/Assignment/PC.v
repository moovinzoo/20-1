`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    16:22:54 12/12/2017 
// Design Name: 
// Module Name:    PC 
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
module PC(
	input CLK, Reset,
	input [7:0] NextPC,
	output reg [7:0] CurrentPC
   );
	
	always @(posedge CLK or posedge Reset) begin
		if(Reset) begin
			CurrentPC <= 8'd0;
		end
		else begin
			CurrentPC <= NextPC;
		end
	end
	
endmodule
