`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    14:56:12 12/12/2017 
// Design Name: 
// Module Name:    Registers 
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
module Registers(
	input CLK, Reset, RegWrite,
	input [1:0] Instruction1, Instruction2, MUX2Output,
	input [7:0] RegWriteDataIn,
	output [7:0] ReadData1, ReadData2, RegWriteDataOut
//	output reg [7:0] RegWriteDataOut
	);
	
	reg [7:0] Reg0, Reg1, Reg2, Reg3; // 4 registers

	assign RegWriteDataOut = RegWriteDataIn;

	assign ReadData1 = (Instruction1 == 2'd0) ? Reg0 :
							 (Instruction1 == 2'd1) ? Reg1 :
							 (Instruction1 == 2'd2) ? Reg2 :
							 (Instruction1 == 2'd3) ? Reg3 : 0;
	assign ReadData2 = (Instruction2 == 2'd0) ? Reg0 :
					  		 (Instruction2 == 2'd1) ? Reg1 :
					 		 (Instruction2 == 2'd2) ? Reg2 :
							 (Instruction2 == 2'd3) ? Reg3 : 0;
			
	always @(posedge CLK or posedge Reset) begin
		if(Reset) begin
			Reg0 <= 7'd0;
			Reg1 <= 7'd0;
			Reg2 <= 7'd0;
			Reg3 <= 7'd0;
		end
		else if(RegWrite) begin
			case(MUX2Output)
				2'd0: Reg0 <= RegWriteDataIn;
				2'd1: Reg1 <= RegWriteDataIn;
				2'd2: Reg2 <= RegWriteDataIn;
				2'd3: Reg3 <= RegWriteDataIn;
			endcase	
		end
	end
	
//	always @(posedge CLK) begin
//		RegWriteDataOut <= RegWriteDataIn;
//	end
	
endmodule
