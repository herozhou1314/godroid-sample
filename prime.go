package prime

func MaxPrime(n int) int {
	return sieve(n)
}

// Send the sequence 2, 3, 4, … to channel 'ch'.
func generate(ch chan<- int, n int) {
	for i := 2; i <= n; i++ {
		ch <- i // Send 'i' to channel 'ch'.
	}
	ch <- -1
}

// Copy the values from channel 'src' to channel 'dst',
// removing those divisible by 'prime'.
func filter(src <-chan int, dst chan<- int, prime int) {
	for i := range src { // Loop over values received from 'src'.
		if i%prime != 0 {
			dst <- i // Send 'i' to channel 'dst'.
		}
	}
}

// The prime sieve: Daisy-chain filter processes together.
func sieve(n int) int {
	ch := make(chan int) // Create a new channel.
	go generate(ch, n)   // Start generate() as a subprocess.
	p := 0
	for {
		prime := <-ch
		if prime < 0 {
			return p
		} else {
			p = prime
		}
		ch1 := make(chan int)
		go filter(ch, ch1, prime)
		ch = ch1
	}
}
