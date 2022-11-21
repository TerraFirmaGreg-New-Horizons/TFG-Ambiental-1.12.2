package com.lumintorious.ambiental.modifiers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;

public class TempModifierStorage implements Iterable<TempModifier>{
	private HashMap<String, TempModifier> map = new HashMap<String, TempModifier>();
	
	private TempModifier put(String key, TempModifier value) {
		if((value.getChange() == 0f && value.getPotency() == 0f)) {
			return null;
		}
		TempModifier modifier = map.get(key);
		if(modifier != null) {
			modifier.absorb(value);
			return modifier;
		}else {
			return map.put(key, value);
		}
	}
	
	public TempModifier add(TempModifier value) {
		if(value == null) {
			return null;
		}
		return this.put(value.getUnlocalizedName(), value);
	}
	
	public boolean contains(String key) {
		return map.containsKey(key);
	}
	
	public boolean contains(TempModifier value) {
		return map.containsValue(value);
	}
	
	public TempModifier get(String key) {
		return map.get(key);
	}
	
	public float getTotalPotency() {
		float potency = 1f;
		for(Map.Entry<String, TempModifier> entry : map.entrySet()) {
			potency += entry.getValue().getPotency();
		}
		return potency;
	}
	
	public float getTargetTemperature() {
		float change = 0f;
		for(Map.Entry<String, TempModifier> entry : map.entrySet()) {
			change += entry.getValue().getChange();
		}
		return change;
	}
	
	public void forEach(Consumer<TempModifier> func) {
		map.forEach((k, v) -> {func.accept(v);});
	}

	@Override
	public Iterator<TempModifier> iterator() {
		Map<String, TempModifier> map1 = map;
		return new Iterator<TempModifier>() {
			private Iterator<Map.Entry<String, TempModifier>> mapIterator = map1.entrySet().iterator();

			@Override
			public boolean hasNext() {
				return mapIterator.hasNext();
			}

			@Override
			public TempModifier next() {
				return mapIterator.next().getValue();
			}
		};
	}
}
