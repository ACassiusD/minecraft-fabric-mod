## This folder contains Item Model Definition Files

```
Example File - resources/assets/tutorial/items/custom_item.json
{
    "model": {
    "type": "model",
    "model": "tutorial:item/custom_item"
    }
}
```

Explanation:
- `"type": "model"`
Tells Minecraft this entry is a model definition, not a built-in or other type of item.

- `"model": "tutorial:item/custom_item"`
Points to your actual model file located in models/item/custom_item.json. This links your registered item to its visual model.

🟡 Purpose:
Required since 1.21.4+ to explicitly associate your item with a custom model. Without this file, custom items may not render properly in-game.