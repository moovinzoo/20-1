`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    14:56:23 12/12/2017 
// Design Name: 
// Module Name:    DataMemory 
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
module DataMemory(
	input CLK, Reset, MemRead, MemWrite,
	input [7:0] Address, WriteData,
	output [7:0] ReadData
   );
	
	reg [7:0] Row [31:0]; // 8-bit word and 32 rows
	
	assign ReadData = (MemRead) ? Row[Address] : 0;
		
	always @(posedge CLK or posedge Reset) begin
		if(Reset) begin
			Row[0] <= 8'd0;
			Row[1] <= 8'd1;
			Row[2] <= 8'd2;
			Row[3] <= 8'd3;
			Row[4] <= 8'd4;
			Row[5] <= 8'd5;
			Row[6] <= 8'd6;
			Row[7] <= 8'd7;
			Row[8] <= 8'd8;
			Row[9] <= 8'd9;
			Row[10] <= 8'd10;
			Row[11] <= 8'd11;
			Row[12] <= 8'd12;
			Row[13] <= 8'd13;
			Row[14] <= 8'd14;
			Row[15] <= 8'd15;
			
			Row[16] <= 8'd0;
			Row[17] <= -8'd1;
			Row[18] <= -8'd2;
			Row[19] <= -8'd3;
			Row[20] <= -8'd4;
			Row[21] <= -8'd5;
			Row[22] <= -8'd6;
			Row[23] <= -8'd7;
			Row[24] <= -8'd8;
			Row[25] <= -8'd9;
			Row[26] <= -8'd10;
			Row[27] <= -8'd11;
			Row[28] <= -8'd12;
			Row[29] <= -8'd13;
			Row[30] <= -8'd14;
			Row[31] <= -8'd15;
		end
		else if(MemWrite) begin
			Row[Address[1:0]] <= WriteData;
		end
	end

endmodule
