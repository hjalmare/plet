# plet

A basic parallel let implementation

## Usage

Plet

```
(defn delayed
  [id duration ret]
  (println "[" id "] started")
  (Thread/sleep duration)
  (println "[" id "] complete")
  ret)


;; Using let the following code will take around 1300ms to run
(let [a (delayed "a" 500 1)
           b (delayed "b" 800 2)
           sum (+ a b)]
          (println "Result: " sum))


;; With plet it will execute in around 800ms
(plet [a (delayed "a" 500 1)
           b (delayed "b" 800 2)
           sum (+ a b)]
          (println "Result: " sum))
```




## License

Copyright © 2017 cwFIXME

Distributed under the Eclipse Public License version 1.0
