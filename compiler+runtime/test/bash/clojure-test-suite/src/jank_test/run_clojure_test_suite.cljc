(ns jank-test.run-clojure-test-suite
  (:require [clojure.test :as t]))

(def namespaces
  '[
    ;clojure.core-test.abs    ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.aclone ; TODO: port aclone
    ;clojure.core-test.add-watch ; Exception: TODO: port sync
    ;clojure.core-test.and     ; Assertion failed!, unloadable.
    clojure.core-test.any-qmark
    ;clojure.core-test.associative-qmark ; TODO: port to-array
    ;clojure.core-test.bigdec ; Read error (312 - 314): invalid number: chars 'M' are invalid for radix 10
    ;clojure.core-test.bigint ; Unable to resolve symbol 'clojure.lang.BigInt'.
    ;clojure.core-test.binding ; TODO: port future
    ;clojure.core-test.bit-and ; Uncaught exception: invalid object type: 0
    ;clojure.core-test.bit-and-not ; Uncaught exception: invalid object type: 0
    ;clojure.core-test.bit-clear ;invalid object type: 0
    ;clojure.core-test.bit-flip ;invalid object type: 0
    ;clojure.core-test.bit-not ; Uncaught exception: invalid object type: 0
    ;clojure.core-test.bit-or ; Uncaught exception: invalid object type: 0
    ;clojure.core-test.bit-set ; invalid object type: 0
    ;clojure.core-test.bit-shift-left ; invalid object type: 0
    ;clojure.core-test.bit-shift-right  ; invalid object type: 0
    ;clojure.core-test.bit-test ; Uncaught exception: invalid object type: 0
    ;clojure.core-test.bit-xor ; Uncaught exception: invalid object type: 0
    ;clojure.core-test.boolean ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.bound-fn ; TODO: port future
    ;clojure.core-test.bound-fn-star ; TODO: port future
    clojure.core-test.butlast
    ;clojure.core-test.byte ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.char ; TODO: port char
    ;clojure.core-test.char-qmark ; Read error (405 - 423): number out of rangeExpecting whitespace after the last token. due to M.
    ;clojure.core-test.compare ; Uncaught exception: not comparable: []
    ;clojure.core-test.count ;https://github.com/jank-lang/jank/issues/244
    ;clojure.core-test.dec ; TODO underflow \n Uncaught exception: failed
    ;clojure.core-test.decimal-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.denominator ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.double ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.double-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.drop ; Uncaught exception: not a number: nil, https://github.com/jank-lang/jank/issues/243 , https://github.com/jank-lang/jank/issues/245
    ;clojure.core-test.drop-last ; Uncaught exception: not a number: nil, https://github.com/jank-lang/jank/issues/244
    ;clojure.core-test.drop-while ; Assertion failed! val.is_some(), In clojure.core-test.drop-while$fn_8-90, duplicate definition of symbol '_fn_55_1', https://github.com/jank-lang/jank/issues/243 , https://github.com/jank-lang/jank/issues/212
    ;clojure.core-test.even-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.false-qmark ; Expecting whitespace after the last token. due to M.
    clojure.core-test.first ; In clojure.core-test.first$fn_2-61, duplicate definition of symbol '_jank_global_init_60'
    ;clojure.core-test.float ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.float-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.fnil  ; Assertion failed! ret, unloadable.
    ;clojure.core-test.format ;TODO: port format
    ;clojure.core-test.get ; Assertion failed! ret, unloadable.
    ;clojure.core-test.ident-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.identical-qmark  ; Assertion failed! ret, unloadable
    ;clojure.core-test.inc ; overflow, overflow untested, Uncaught exception: not a number: nil.
    ;clojure.core-test.int ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.int-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.integer-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.intern ;unloadable
    ;clojure.core-test.keyword ;https://github.com/jank-lang/jank/issues/246
    ;clojure.core-test.keyword-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.long  ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.max ; Unable to resolve symbol 'Exception'.
    ;clojure.core-test.min ; Uncaught exception: not a number: "x"
    ;clojure.core-test.minus ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.mod ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.name ; Uncaught exception: not nameable: nil, In clojure.core-test.name$fn_2-59, duplicate definition of symbol '_jank_global_init_58'
    ;clojure.core-test.namespace ; Uncaught exception: not nameable: nil, https://github.com/jank-lang/jank/issues/254
    ;clojure.core-test.nan-qmark ; Uncaught exception: not a number: nil, https://github.com/jank-lang/jank/issues/244
    ;clojure.core-test.neg-int-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.neg-qmark ; Expecting whitespace after the last token. due to M.
    clojure.core-test.next
    clojure.core-test.nil-qmark
    ;clojure.core-test.not ;Read error (478 - 478): unsupported reader macro
    ;clojure.core-test.nth ; Uncaught exception: index out of bounds: -1, In clojure.core-test.nth$fn_2-64, duplicate definition of symbol '_fn_2_0', https://github.com/jank-lang/jank/issues/244, Exception: index out of bounds: -1
    ;clojure.core-test.nthnext ; Uncaught exception: not a number: nil, In clojure.core-test.nthnext$fn_2-83, duplicate definition of symbol '_fn_2_0', https://github.com/jank-lang/jank/issues/243 , https://github.com/jank-lang/jank/issues/244
    ;clojure.core-test.nthrest ; Uncaught exception: not a number: nil, In clojure.core-test.nthrest$fn_2-85, duplicate definition of symbol '_fn_2_0', https://github.com/jank-lang/jank/issues/243 , https://github.com/jank-lang/jank/issues/244 , https://github.com/jank-lang/jank/issues/247
    ;clojure.core-test.num ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.number-qmark ; Expecting whitespace after the last token. due to M.
    clojure.core-test.number-range
    ;clojure.core-test.numerator ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.odd-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.or ; unloadable, In clojure.core-test.or$fn_2-159, duplicate definition of symbol '_fn_2_0', unloadable
    ;clojure.core-test.partial ;unloadable
    ;clojure.core-test.plus ; error: Unable to resolve symbol 'Exception'
    ;clojure.core-test.plus-squote ; error: Unable to resolve symbol 'clojure.lang.BigInt'.
    ;clojure.core-test.pos-int-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.pos-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.pr-str ; Uncaught exception: invalid call to clojure.core/pr-str with 2 args provided, In clojure.core-test.pr-str$fn_2-49, duplicate definition of symbol '_fn_2_0', unloadable
    ;clojure.core-test.print-str ; TODO: port print-str, In clojure.core-test.print-str$fn_2-49, duplicate definition of symbol '_jank_global_init_48', unloadable
    ;clojure.core-test.println-str ; unloadable, In clojure.core-test.println-str$fn_2-51, duplicate definition of symbol '_fn_2_0', unloadable
    ;clojure.core-test.prn-str ; unloadable, In clojure.core-test.prn-str$fn_2-51, duplicate definition of symbol '_fn_2_0', unloadable
    ;clojure.core-test.qualified-ident-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.qualified-keyword-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.qualified-symbol-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.quot ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.rand ; unloadable, In clojure.core-test.rand$fn_2-71, duplicate definition of symbol '_jank_global_init_70', unloadable
    ;clojure.core-test.rand-int ; unloadable, In clojure.core-test.rand-int$fn_2-56, duplicate definition of symbol '_fn_2_0', unloadable
    ;clojure.core-test.ratio-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.rational-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.rationalize ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.rem ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.remove-watch ;Exception: TODO: port sync
    clojure.core-test.rest
    clojure.core-test.second
    ;clojure.core-test.seq ; error: Characters 'M' are invalid for a base 10 number.
    ;clojure.core-test.sequential-qmark ; TODO: port to-array, In clojure.core-test.sequential-qmark$fn_2-98, duplicate definition of symbol '_fn_2_0', unloadable
    ;clojure.core-test.short ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.simple-ident-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.simple-keyword-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.simple-symbol-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.slash ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.some-qmark ;Read error (437 - 437): unsupported reader macro
    ;clojure.core-test.star ; error: Unable to resolve symbol 'Exception'.
    ;clojure.core-test.star-squote ; error: Unable to resolve symbol 'clojure.lang.BigInt'.
    ;clojure.core-test.str ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.string-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.subs ; Uncaught exception: end index 1 is less than start 2, In clojure.core-test.subs$fn_2-70, duplicate definition of symbol '_fn_2_0', https://github.com/jank-lang/jank/issues/244
    ;clojure.core-test.symbol  ; Read error (1409 - 1414): invalid ratio: expecting an integer denominator
    ;clojure.core-test.symbol-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.take ; Uncaught exception: not a number: nil, In clojure.core-test.take$fn_2-57, duplicate definition of symbol '_fn_2_0', https://github.com/jank-lang/jank/issues/245 , https://github.com/jank-lang/jank/issues/243
    ;clojure.core-test.take-last ; Uncaught exception: not a number: nil, In clojure.core-test.take-last$fn_2-43, duplicate definition of symbol '_fn_2_0', https://github.com/jank-lang/jank/issues/243 , https://github.com/jank-lang/jank/issues/244
    ;clojure.core-test.take-while ; Uncaught exception: invalid call with 1 arg to: nil, In clojure.core-test.take-while$fn_2-86, duplicate definition of symbol '_fn_2_0', https://github.com/jank-lang/jank/issues/243
    ;clojure.core-test.taps ; Read error (0 - 0): unbound symbol: clojure.lang.IPending
    ;clojure.core-test.true-qmark ; Expecting whitespace after the last token. due to M.
    ;clojure.core-test.unsigned-bit-shift-right ; Uncaught exception: invalid object type: 0, In clojure.core-test.unsigned-bit-shift-right$fn_2-37, duplicate definition of symbol '_jank_global_init_36', Exception: invalid object type: 0
    ;clojure.core-test.with-out-str ; Exception: TODO: port with-out-str
    ;clojure.core-test.with-precision ;Read error (372 - 374): invalid number: chars 'M' are invalid for radix 10
    ;clojure.core-test.zero-qmark ; Expecting whitespace after the last token. due to M.
  ])

(defn -main []
  (when (seq namespaces)
    (apply require namespaces)
    ;; TODO (t/run-all-tests) => Exception: "TODO: port all-ns"
    (when-not (t/successful? (apply t/run-tests namespaces))
      (throw "failed")))
  (println :clojure-test-suite-successful))
