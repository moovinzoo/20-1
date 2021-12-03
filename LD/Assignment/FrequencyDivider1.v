`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    16:38:27 12/19/2017 
// Design Name: 
// Module Name:    FrequencyDivider1 
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
module FrequencyDivider1(
	input CLKIn, Reset,
	output reg CLKOut
   );
	
	reg [31:0] Count;
	
	always @(posedge CLKIn or posedge Reset) begin
		if(Reset) begin
			CLKOut <= 1'd0;
		end
		else begin
			if(Count == 32'd25000000) begin // use 50MHz built-in clock
				Count <= 32'd0;
				CLKOut <= ~CLKOut;
			end
			else begin
				Count <= Count + 1;
			end
		end
	end
	
endmodule