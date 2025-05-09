## This folder contains Item Model Files
```
Example File - resources/assets/tutorial/models/item/custom_item.json
{
  "parent": "item/generated",
  "textures": {
    "layer0": "tutorial:item/custom_item"
  }
}
```

Explanation:

- `"parent": "item/generated"`
Inherits basic flat item rendering behavior. Use "item/handheld" instead for tools like swords/pickaxes.


- `"textures": { "layer0": ... }`
Tells Minecraft where to find the texture. layer0 is the base layer. The value refers to `resources/assets/tutorial/textures/item/custom_item.png`

🟢 Purpose: This defines how the item looks in-game (in GUI, hand, dropped).

---

## Basic flat item rendering behavior
Refers to how Minecraft displays most 2D items (like ingots, food, or dyes) in the GUI, inventory, and hand.

When an item model uses

"parent": "item/generated"
…it inherits a default flat, sprite-based rendering. That means:

- The item appears like a flat image in the inventory.

- It renders flat and upright when held in the player's hand (not angled like tools).

- It doesn't have 3D geometry — it's just a textured square.

This is ideal for non-tools. Tools (swords, pickaxes, etc.) use "item/handheld" for angled-in-hand rendering.

Want to see a side-by-side of item/generated vs item/handheld render styles?