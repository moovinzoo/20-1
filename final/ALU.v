`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    14:36:24 12/12/2017 
// Design Name: 
// Module Name:    ALU 
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
module ALU(
	input ALUOp,
	input [7:0] ReadData1, MUX3Output,
	output [7:0] ALUOutput
   );
	
	assign ALUOutput = ALUOp ? (ReadData1 + MUX3Output) : 7'd0;

endmodule
