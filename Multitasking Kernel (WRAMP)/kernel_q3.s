#Multitasking Kernel - Kelvin David 1345360
#Question 03 - Timer Interupts + Serial Task
#==========================================
#The function of this program is to produce
#timer interupts to increment the counter 
#variable in serial Task

.global main
.text

main:
	movsg	$2, $cctrl		#load $cctrl
	andi	$2, $2, 0x000F		#clear exceptions
	ori	$2, $2, 0x4D		#set timer exceptions
	movgs	$cctrl, $2		#reload $cctrl
	
	sw	$0, TimerAck($0)	#clear existing timer exceptions
	addui	$2, $0, 24		#set to 100 interupts per second
	sw	$2, TimerLoad($0)	#load tick rate
	addui	$2, $0, 0x3		#set automatic reset + enable
	sw	$2, TimerControl($0)	#load to start timer
	
	movsg	$2, $evec		#load default handler
	sw	$2, old_vector($0)	#save it in memory
	la	$2, handler		#load custom handler
	movgs	$evec, $2		#store as new default handler
	
	jal	serial_main		#jump to serial main
	
handler:
	movsg	$13, $estat		#get exception
	andi	$13, $13, 0xFFB0	#if we handle (timer inter)
	beqz	$13, count		#goto count
	lw	$13, old_vector($0)	#else load old handler
	jr	$13			#goto old handler
	
count:
	sw	$0, TimerAck($0)	#acknowledge timer interrupt
	lw	$13, counter($0)	#load counter
	addui	$13, $13, 1		#increment counter by 1
	sw	$13, counter($0)	#re store counter

	rfe				#return from exception

.bss
old_vector:
	.word

.equ	TimerControl,	0x72000
.equ	TimerLoad,	0x72001
.equ	TimerAck,	0x72003
