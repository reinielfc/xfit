# Contributing

## Git

### Commits

* 1 topic per commit.
* Keep the message descriptive, but short and sweet.
  * **Hint:** If the message is too long, there might be more than 1 topic involved.
* For the body, describe (if necessary):
  * What's different?
  * Reason for change.
  * Stuff to watch out for / anything particularly remarkable.

### Branches

#### Long-running

* **`main` branch:** For 'production' code only.
* **`dev` branch:** For code in development.

_DO NOT COMMIT directly to these branches._

#### Short-lived

Created for new features, releases, bug fixes, refactorings, experiments...  
Branched out from `dev` branch.

_These branches are deleted after integration._

#### Naming

New branches should follow the following naming convention:

```
<author>-<type>-<NameInUpperCamelCase>
```
