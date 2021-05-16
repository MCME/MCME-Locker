# MCME-Locker
This plugin allows servers to be locked and only allow a specific group of people to join (or remain) in the server.
## Config
`default_status` can have 2 values either `LOCKED` or `UNLOCKED` other values will default to unlocked

`move_to_world` this is the server that people will be moved to instead of kicked. If not present, a kick will be used.

`execute_permissions` these permissions are allowed to use the commands

`kick_permissions` these permissions will be kicked once the lock is activated

`remain_permissions` these permissions will remain once lock is activated

`exclude_permissions` these permissions will not be impacted by the plugin

Note: If both kick and remain are filled in the application won't start.

Note: If both kick and reamin are empty, everyone will be kicked (besides exclude)


## Commands
`/locker lock` locks the server

`/locker unlock` unlocks the server

`/locker toggle` toggles the current lock-status