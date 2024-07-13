# Change Log
All notable changes to this project will be documented in this file. This change log follows the conventions of [keepachangelog.com](http://keepachangelog.com/).

## 1.0.0 (2024-07-13)


### Features

* re-write the logic using equality, instead of sum ([a58a6c6](https://github.com/WarFox/tic-tac-toe/commit/a58a6c6c1ad784244060f64381cf7a97838d41db))
* use :x to represent X and :o to represent O in tests ([bc82191](https://github.com/WarFox/tic-tac-toe/commit/bc82191945378fd8052d167088412c11ab94d6e1))
* use nil to represent empty, instead of using 0 ([7f00c83](https://github.com/WarFox/tic-tac-toe/commit/7f00c83da976fdd2efd28fa15924e277bbb886de))


### Bug Fixes

* handle case when row has all nils, nil is not a winner ([9e6a8ca](https://github.com/WarFox/tic-tac-toe/commit/9e6a8ca8e1471e8b6e3168969782123f9511bb88))
* re-order tests to same order as their functions ([9da6c21](https://github.com/WarFox/tic-tac-toe/commit/9da6c2197dea8ba736806bfd35bd63b8f0efc9d2))

## [Unreleased]

See unreleased changes [here](https://github.com/Warfox/tic-tac-toe/compare/v1.0.0...HEAD)

## Pre Release

- add linting with clj-kondo
- linting Update readme and add image
- add test cases for :no-winner scenarios
- add logic for checking winner diagonally
- add logic for checking winner vertically
- update logic to check winner for -1; -1 wins when sum is -3
- check winner in rows; 1 is winner if sum is 3
- add default board
- add github workflow for running tests

[Unreleased]: https://github.com/Warfox/tic-tac-toe/compare/v1.0.0...HEAD
