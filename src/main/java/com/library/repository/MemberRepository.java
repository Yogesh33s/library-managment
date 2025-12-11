package com.library.repository;

import com.library.model.Member;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MemberRepository {

    private final Map<Integer, Member> members = new HashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public Member save(Member member) {
        int id = idCounter.getAndIncrement();
        Member newMember = new Member(id, member.getName(), member.getEmail());
        members.put(id, newMember);
        return newMember;
    }

    public Optional<Member> findById(int id) {
        return Optional.ofNullable(members.get(id));
    }

    public List<Member> findAll() {
        return new ArrayList<>(members.values());
    }

    public boolean delete(int id) {
        return members.remove(id) != null;
    }

    public void clear() {
        members.clear();
    }
}
