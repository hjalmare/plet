(ns plet.core-test
  (:require [clojure.test :refer :all]
            [plet.core :refer :all]))


(defn delayed
  [id duration ret]
  (println "[" id "] started")
  (Thread/sleep duration)
  (println "[" id "] complete")
  ret)

(deftest a-test
  (testing "Test bindings in let bindings"
    (plet [a (delayed "a" 500 1)
           b (delayed "b" 800 2)
           sum (+ a b)]
          (is 3 sum)))
  (testing "Test bindings in let body"
    (plet [a (delayed "bdy-a" 500 1)
           b (delayed "bdy-b" 800 2)]
          (is 3 (+ a b))))
  (testing "Test bindings in let body with const and fn"
    (plet [a (delayed "cfn-a" 500 1)
           b (delayed "cfn-b" 800 2)
           c 4
           d (fn [] (+ a b))]
          (is 7 (+ c (d))))))
