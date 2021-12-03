`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    18:39:46 12/12/2017 
// Design Name: 
// Module Name:    InstructionMemory 
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
module InstructionMemory(
	input [7:0] ReadAddress,
	output [7:0] Instruction
	);
	
	wire [7:0] MemoryByte [31:0];
	
	// -2, -1, 0, +1
	
//	assign MemoryByte[0] = { 2'd1, 2'd0, 2'd1, 2'd1 }; // load, 0
//	assign MemoryByte[1] = { 2'd1, 2'd1, 2'd2, 2'd1 }; // load, 1
//	assign MemoryByte[2] = { 2'd0, 2'd1, 2'd2, 2'd3 }; // add, 2
//	assign MemoryByte[3] = { 2'd2, 2'd0, 2'd3, 2'd1 }; // store		
//	assign MemoryByte[4] = { 2'd3, 4'd0, 2'd3 }; // jump	
	
	assign MemoryByte[0] = { 2'd1, 2'd0, 2'd1, 2'b01 }; // load, r1: 1
	assign MemoryByte[1] = { 2'd1, 2'd1, 2'd2, 2'b01 }; // load, r2: 2
	assign MemoryByte[2] = { 2'd0, 2'd1, 2'd2, 2'd3 }; // add, r3: 3
	assign MemoryByte[3] = { 2'd2, 2'd0, 2'd3, 2'b00 }; // store, 3																
	assign MemoryByte[4] = { 2'd3, 4'd0, 2'b00 }; // jump, 4
	assign MemoryByte[5] = { 2'd3, 4'd0, 2'b01 }; // jump, 5
	assign MemoryByte[6] = { 2'd3, 4'd0, 2'b01 }; // jump, 7
	assign MemoryByte[7] = { 2'd3, 4'd0, 2'b10 }; // jump, 6
	assign MemoryByte[8] = { 2'd0, 2'd2, 2'd3, 2'd1 }; // add, r1: 5 
	assign MemoryByte[9] = { 2'd0, 2'd1, 2'd3, 2'd2 }; // add, r2: 8
	assign MemoryByte[10] = { 2'd0, 2'd1, 2'd2, 2'd3 }; // add, r3: d
	assign MemoryByte[11] = { 2'd2, 2'd2, 2'd3, 2'd1 }; // store, d
	assign MemoryByte[12] = { 2'd3, 4'b0000, 2'b01 }; // jump, c
	
	assign MemoryByte[14] = { 2'd3, 4'b0000, 2'd3 }; // jump, e
	
	assign Instruction = MemoryByte[ReadAddress];

endmodule
