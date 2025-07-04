#include <jank/runtime/obj/detail/iterator_sequence.hpp>
#include <jank/runtime/core/seq.hpp>
#include <jank/runtime/core/to_string.hpp>
#include <jank/runtime/visit.hpp>

namespace jank::runtime
{
  bool equal(object_ref lhs, object_ref rhs);
}

namespace jank::runtime::obj::detail
{
  template <typename Derived, typename It>
  iterator_sequence<Derived, It>::iterator_sequence(object_ref const &c,
                                                    It const &b,
                                                    It const &e,
                                                    usize const s)
    : coll{ c }
    , begin{ b }
    , end{ e }
    , size{ s }
  {
    if(begin == end)
    {
      throw std::runtime_error{ "iterator_sequence for empty sequence" };
    }
  }

  template <typename Derived, typename It>
  bool iterator_sequence<Derived, It>::equal(object const &o) const
  {
    return runtime::sequence_equal(static_cast<Derived const *>(this), &o);
  }

  template <typename Derived, typename It>
  void iterator_sequence<Derived, It>::to_string(util::string_builder &buff) const
  {
    runtime::to_string(begin, end, "(", ')', buff);
  }

  template <typename Derived, typename It>
  jtl::immutable_string iterator_sequence<Derived, It>::to_string() const
  {
    util::string_builder buff;
    runtime::to_string(begin, end, "(", ')', buff);
    return buff.release();
  }

  template <typename Derived, typename It>
  jtl::immutable_string iterator_sequence<Derived, It>::to_code_string() const
  {
    util::string_builder buff;
    runtime::to_code_string(begin, end, "(", ')', buff);
    return buff.release();
  }

  template <typename Derived, typename It>
  uhash iterator_sequence<Derived, It>::to_hash() const
  {
    return hash::ordered(begin, end);
  }

  template <typename Derived, typename It>
  oref<Derived> iterator_sequence<Derived, It>::seq()
  {
    return static_cast<Derived *>(this);
  }

  template <typename Derived, typename It>
  oref<Derived> iterator_sequence<Derived, It>::fresh_seq() const
  {
    return make_box<Derived>(coll, begin, end, size);
  }

  template <typename Derived, typename It>
  usize iterator_sequence<Derived, It>::count() const
  {
    return size;
  }

  template <typename Derived, typename It>
  object_ref iterator_sequence<Derived, It>::first() const
  {
    return *begin;
  }

  template <typename Derived, typename It>
  oref<Derived> iterator_sequence<Derived, It>::next() const
  {
    auto n(begin);
    ++n;

    if(n == end)
    {
      return {};
    }

    return make_box<Derived>(coll, n, end, size);
  }

  template <typename Derived, typename It>
  oref<Derived> iterator_sequence<Derived, It>::next_in_place()
  {
    ++begin;

    if(begin == end)
    {
      return {};
    }

    return static_cast<Derived *>(this);
  }

  template <typename Derived, typename It>
  obj::cons_ref iterator_sequence<Derived, It>::conj(object_ref const head)
  {
    return make_box<obj::cons>(head, static_cast<Derived *>(this));
  }

  template struct iterator_sequence<persistent_sorted_set_sequence,
                                    runtime::detail::native_persistent_sorted_set::const_iterator>;
  template struct iterator_sequence<persistent_hash_set_sequence,
                                    runtime::detail::native_persistent_hash_set::iterator>;
}
