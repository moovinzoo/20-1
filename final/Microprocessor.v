`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    03:46:33 12/13/2017 
// Design Name: 
// Module Name:    Microprocessor 
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
module Microprocessor(
	input CLK, Reset, PCButton, MemWriteButton,
	input [7:0] Instruction,
	output [7:0] ReadAddress,
	output [6:0] RegWriteDataFront, RegWriteDataBack, MemWriteDataFront, MemWriteDataBack, PCFront, PCBack
   );
	

	
	wire One, ChangedCLK1, ChangedCLK2;
	reg tmpCLK;
	wire [7:0] w1, w2, w3, w4, w5, w15, w16, w17, w18, w19, w20, w21, w22;
	wire [1:0] w14;
	wire w6, w7, w8, w9, w10, w11, w12, w13, w23, w24;
		
	assign One = 1'd1;
	assign ReadAddress = w2;
	assign w5 = Instruction;
//	assign RegWriteDataOut = w22;

//	always @(negedge CLK) begin
//	tmpCLK <= CLK;
//	end
	
	FrequencyDivider1 used1(.CLKIn(CLK), .Reset(Reset), .CLKOut(ChangedCLK1));
	FrequencyDivider2 used21(.CLKIn(CLK), .Reset(Reset), .CLKOut(ChangedCLK2));
	PC used2(.CLK(ChangedCLK1), .Reset(Reset), .NextPC(w1), .CurrentPC(w2));
	Adder used3(.Input1(One), .Input2(w2), .Sum(w3)); // Adder1
	Adder used4(.Input1(w3), .Input2(w15), .Sum(w4)); // Adder2
	MUX used5(.Input1(w3), .Input2(w4), .Selection(w6), .MUXOutput(w1)); // MUX1
	MUX used6(.Input1(w5[3:2]), .Input2(w5[1:0]), .Selection(w13), .MUXOutput(w14)); // MUX2
	MUX used7(.Input1(w16), .Input2(w15), .Selection(w11), .MUXOutput(w17)); // MUX3
	MUX used8(.Input1(w19), .Input2(w21), .Selection(w7), .MUXOutput(w20)); // MUX4
	Control used9(.Instruction(w5[7:6]), .RegDst(w13), .RegWrite(w12), .ALUSrc(w11), .Branch(w6), .MemRead(w8), .MemWrite(w9), .MemtoReg(w7), .ALUOp(w10), .DCFront(w23), .DCBack(w24));
	Registers used10(.CLK(ChangedCLK2), .Reset(Reset), .RegWrite(w12), .Instruction1(w5[5:4]), .Instruction2(w5[3:2]), .MUX2Output(w14), .RegWriteDataIn(w20), .ReadData1(w18), .ReadData2(w16), .RegWriteDataOut(w22));
	SignExtend used11(.Instruction(w5[1:0]), .SignExtendOutput(w15));
	ALU used12(.ALUOp(w10), .ReadData1(w18), .MUX3Output(w17), .ALUOutput(w19));
	DataMemory used13(.CLK(ChangedCLK2), .Reset(Reset), .MemRead(w8), .MemWrite(w9), .Address(w19), .WriteData(w16), .ReadData(w21));
	
	SevenSegmentDecoder used14(.Number(w22[7:4]), .DC(w23), .Display(RegWriteDataFront)); // RegWrite
	SevenSegmentDecoder used15(.Number(w22[3:0]), .DC(w23), .Display(RegWriteDataBack)); // RegWrite
	SevenSegmentDecoderMemWrite used17(.Button(MemWriteButton), .Number(w16[7:4]), .DC(w24), .Display(MemWriteDataFront)); // MemWrite
	SevenSegmentDecoderMemWrite used18(.Button(MemWriteButton), .Number(w16[3:0]), .DC(w24), .Display(MemWriteDataBack)); // MemWrite
	SevenSegmentDecoderPC used19(.Button(PCButton), .Number(w2[7:4]), .Display(PCFront)); // CurrentPC
	SevenSegmentDecoderPC used20(.Button(PCButton), .Number(w2[3:0]), .Display(PCBack)); // CurrentPC
	
//	InstructionMemory used16(.ReadAddress(w2), .Instruction(w5));

endmodule


		
//	always @(negedge CLK) begin
//		tmpCLK <= CLK; 
//	end

//	Enable used21(.CLKIn(CLK), .Reset(Reset), .CLKOut(Enable));