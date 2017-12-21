(ns plet.core)

(defn sym-list
  [col [sy _ & re]]
  (if re
    (recur (conj col sy) re)
    (conj col sy)))

(defn rebind
  [tbl expr]
  (if (seq? expr)
      (doall (map (fn [it] (rebind tbl it)) expr))
      (if (and (symbol? expr) (contains? tbl expr))
        (list 'deref expr)
        expr)))

(defn rebind-bindings
  [col tbl [a b & re]]
  (let [bnd [a (list 'future (rebind tbl b))]
        ret (into col bnd)]
    (if re
      (recur ret tbl re)
      ret)))

(defmacro plet
  [bindings body]
  (let [tbl (sym-list #{} bindings)
        b2 (rebind-bindings [] tbl bindings)]
    (list 'let b2 (rebind tbl body))))
