`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    14:46:04 12/12/2017 
// Design Name: 
// Module Name:    Control 
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
module Control(
	input [1:0] Instruction,
	output RegDst, RegWrite, ALUSrc, Branch, MemRead, MemWrite, MemtoReg, ALUOp, DCFront, DCBack
   );
	
	assign RegDst = (Instruction == 2'd0) ? 1 : 0;
	assign RegWrite = ((Instruction == 2'd0) || (Instruction == 2'd1)) ? 1 : 0;
	assign ALUSrc = ((Instruction == 2'd1) || (Instruction == 2'd2)) ? 1 : 0;
	assign Branch = (Instruction == 2'd3) ? 1 : 0;
	assign MemRead = (Instruction == 2'd1) ? 1 : 0;
	assign MemWrite = (Instruction == 2'd2) ? 1 : 0;
	assign MemtoReg = (Instruction == 2'd1) ? 1 : 0;
	assign ALUOp = (Instruction == 2'd3) ? 0 : 1;
	
	assign DCFront = ((Instruction == 2'd2) || (Instruction == 2'd3)) ? 1 : 0;
	assign DCBack = ((Instruction == 2'd0) || (Instruction == 2'd1) || (Instruction == 2'd3)) ? 1 : 0;

endmodule
