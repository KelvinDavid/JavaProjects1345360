#Multitasking Kernel - Kelvin David 1345360
#Question 02 - Serial Task
#==========================================
#The function of this program is to be able
#to print 3 time formats mm:ss ssss.ss and intCount
#it must be able it switch between them at will

.global serial_main
.global counter
.text

serial_main:
	subi	$sp, $sp, 1		#push stack
	sw	$ra, 0($sp)		#save $ra

loop:
	lw	$2, format($0)		#read current format

	sequi	$3, $2, 1		#if first flag
	bnez	$3, frmst1		#go first format

	sequi	$3, $2, 2		#if second flag
	bnez	$3, frmst2		#go second format

	sequi	$3, $2, 3		#if thrid flag
	bnez	$3, frmst3		#go thrid format
		
read:
	lw	$4, SerialStatus($0)	#check Sp2 status
	andi	$4, $4, 0x1		#if ready to recieve
	beqz	$4, loop		#if not poll

	lw	$2, SerialReceive($0)	#read retrieved char
	
	sequi	$3, $2, 0x31		#if '1' set to format 1
	bnez	$3, set1

	sequi	$3, $2, 0x32		#if '2' set to format 2
	bnez	$3, set2

	sequi	$3, $2, 0x33		#if '3' set to format 3
	bnez	$3, set3

	sequi	$3, $2, 0x71		#if 'q' terminate program
	bnez	$3, end

	j 	loop			#return to loop
end:
	lw	$ra, 0($sp)		#reload $ra
	addui	$sp, $sp, 1		#pop stack
	jr	$ra			#goto $ra

set1:
	addui	$3, $0, 1
	sw	$3, format($0)		#sets flag for mm:ss
	j 	loop
set2:
	addui	$3, $0, 2
	sw	$3, format($0)		#sets flag for ssss.ss 
	j	loop
set3:
	addui	$3, $0, 3
	sw	$3, format($0)		#sets flag for ttttttt
	j	loop

frmst1:
	lw	$4, counter($0)		#load counter
	divui	$4, $4, 100		#convert to seconds
	remui	$4, $4, 60		#convert to correct seconds format

	remui	$5, $4, 10
	addui	$5, $5, '0'
	addui	$6, $0, 5		#store seconds digit 1
	sw	$5, frm1($6)
	divui	$4, $4, 10

	remui	$5, $4, 10
	addui	$5, $5, '0'		#store seconds digit 2
	addui	$6, $0, 4
	sw	$5, frm1($6)

	lw	$4, counter($0)		#load counter again
	divui	$4, $4, 100		#convert to seconds
	divui	$4, $4, 60		#convert to minutes

	remui	$5, $4, 10
	addui	$5, $5, '0'
	addui	$6, $0, 2		#store minute digit 1
	sw	$5, frm1($6)
	divui	$4, $4, 10

	remui	$5, $4, 10
	addui	$5, $5, '0'		#store minute digit 2
	addui	$6, $0, 1
	sw	$5, frm1($6)

	addui	$5, $0, 0		#loop counter to 0
form1:
	lw 	$4, SerialStatus($0)
	andi	$4, $4, 0x2		#check Sp2 transmit ready
	beqz	$4, form1 

	lw	$3, frm1($5)		#holds calc time in 'mm:ss'
	sw	$3, SerialTransmit($0)	#transmit time using loop
	addui	$5, $5, 1

	sequi	$3, $5, 8
	beqz	$3, form1
	
	j read
frmst2:
	lw	$4, counter($0)		#load counter

	remui	$5, $4, 10
	addui	$5, $5, '0'
	addui	$6, $0, 7		#store counter digits
	sw	$5, frm2($6)
	divui	$4, $4, 10

	remui	$5, $4, 10
	addui	$5, $5, '0'
	addui	$6, $0, 6
	sw	$5, frm2($6)
	divui	$4, $4, 10

	remui	$5, $4, 10
	addui	$5, $5, '0'
	addui	$6, $0, 4		#repeat 6 times
	sw	$5, frm2($6)
	divui	$4, $4, 10

	remui	$5, $4, 10
	addui	$5, $5, '0'
	addui	$6, $0, 3
	sw	$5, frm2($6)
	divui	$4, $4, 10

	remui	$5, $4, 10
	addui	$5, $5, '0'
	addui	$6, $0, 2
	sw	$5, frm2($6)
	divui	$4, $4, 10

	remui	$5, $4, 10
	addui	$5, $5, '0'
	addui	$6, $0, 1
	sw	$5, frm2($6)

	addui	$5, $0, 0		#reset loop
form2:
	lw 	$4, SerialStatus($0)
	andi	$4, $4, 0x2		#check Sp2 transmit ready
	beqz	$4, form2 

	lw	$3, frm2($5)		#holds calc time in 'ssss.ss'
	sw	$3, SerialTransmit($0)	#transmit time using loop
	addui	$5, $5, 1

	sequi	$3, $5, 8
	beqz	$3, form2
	
	j read
frmst3:
	lw	$4, counter($0)		#load counter

	remui	$5, $4, 10
	addui	$5, $5, '0'
	addui	$6, $0, 6		#store count digits
	sw	$5, frm3($6)
	divui	$4, $4, 10

	remui	$5, $4, 10
	addui	$5, $5, '0'
	addui	$6, $0, 5
	sw	$5, frm3($6)
	divui	$4, $4, 10

	remui	$5, $4, 10
	addui	$5, $5, '0'
	addui	$6, $0, 4
	sw	$5, frm3($6)
	divui	$4, $4, 10

	remui	$5, $4, 10
	addui	$5, $5, '0'
	addui	$6, $0, 3		#repeat 6 times
	sw	$5, frm3($6)
	divui	$4, $4, 10

	remui	$5, $4, 10
	addui	$5, $5, '0'
	addui	$6, $0, 2
	sw	$5, frm3($6)
	divui	$4, $4, 10

	remui	$5, $4, 10
	addui	$5, $5, '0'
	addui	$6, $0, 1
	sw	$5, frm3($6)

	addui	$5, $0, 0		#reset counter
form3:
	lw 	$4, SerialStatus($0)
	andi	$4, $4, 0x2		#check Sp2 transmit ready
	beqz	$4, form3 

	lw	$3, frm3($5)		#holds calc time in 'tttttt'
	sw	$3, SerialTransmit($0)	#print time using loop
	addui	$5, $5, 1

	sequi	$3, $5, 8
	beqz	$3, form3
	
	j read
.data
counter:
	.word 0
format:
	.word 1
frm1:
	.asciiz "\rmm:ss  "
frm2:
	.asciiz "\rssss.ss"
frm3:
	.asciiz "\rtttttt "
.bss
old_vector:
	.word

.equ	TimerControl,	0x72000
.equ	TimerLoad,	0x72001
.equ	TimerAck,	0x72003

.equ	SerialStatus, 	0x71003
.equ	SerialReceive, 	0x71001
.equ	SerialTransmit, 0x71000	
