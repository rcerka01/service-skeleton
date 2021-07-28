# Architecture Decision Record

## Context

Architecturally significant design decisions are usually influenced by (and seek to find compromise between) multiple competing factors and constraints. These may be well understood at the time of making decision, but are inevitably forgotten as we move on to work on other things.

We currently rely on word of mouth to keep maintain past-knowledge of our software

## Decision
- We will keep a record of design decisions alongside the code
- We will follow the format proposed here: http://thinkrelevance.com/blog/2011/11/15/documenting-architecture-decisions
- Each design decision will be captured in `/docs/arch/adr-xxx.md`
- Design decisions that are no longer relevant will be renamed to `/docs/arch/adr-xxx-superceded.md` and updated to reflect why they are no longer applicable

## Status
Accepted

## Consequences

Our future selves will be able to reason about decisions made in the past, the context surrounding them, and whether they still impact on current decisions
